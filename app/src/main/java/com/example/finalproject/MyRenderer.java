package com.example.finalproject;

import android.app.Activity;
import android.opengl.GLES30;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import gl.renderers.GyroscopicRenderer;

public class MyRenderer extends GyroscopicRenderer implements View.OnTouchListener {

    List<TunnelSegment> segments = new ArrayList<>();
    float tunnelSpeed = 1f;
    float segmentLength = 3f;
    float segmentCount = 15;
    float tunnelRotation = 0f;
    public int score = 0;
    TextView scoreText;

    Activity activity;
    Character character;

    Asteroid asteroid;

    boolean finalCollision = false;

    public MyRenderer(Activity _activity, TextView scoreText){
        super(_activity);
        this.activity=_activity;
        this.scoreText = scoreText;
    }

    @Override
    public void setup() {

        character = new Character();
        float startingPos = -3f;
        character.positionZ = startingPos;
        character.positionY = -1.0f;
        character.positionX = 0;
        character.localTransform.translate(0, 0, startingPos);
        character.localTransform.updateShader();

        for (int i = 0; i < segmentCount; i++) {
            TunnelSegment segment = new TunnelSegment(activity);
            segment.z_position = -i * segmentLength;
            segment.localTransform.translate(0, 0, -5);
            segment.localTransform.updateShader();
            segments.add(segment);
        }

        background(0f,0f,0f);
        setLightDir(0,-1,-1);

        setRotationCenter(0,0,-5);
        setFOV(80);
    }

    @Override
    public void setupView() {
        view.identity();
        view.translate(0, 0, 0);
    }

    double lastTime=0;

    @Override
    public void simulate(double elapsedDisplayTime) {

        float perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        if (tunnelSpeed < 10) tunnelSpeed += 0.001f;

        for (TunnelSegment s : segments) {
            s.z_position += tunnelSpeed * perSec;

            if (s.z_position > 2f) {
                s.z_position -= segmentCount * segmentLength;
            }

            s.localTransform.identity();
            s.localTransform.rotateZ(tunnelRotation);
            s.localTransform.translate(0, 0, s.z_position);
            s.localTransform.updateShader();

            for (Asteroid a : s.asteroids) {
                a.update(perSec, s.z_position, tunnelRotation);

                float dx = a.positionX - character.positionX;
                float dy = a.positionY - character.positionY;
                float dz = a.positionZ - character.positionZ;

                float minDist = a.collisionRadius + character.collisionRadius;
                //Log.d("COLLISION", "Asteroid: " + a.positionX + ", " + a.positionY + ", " + a.positionZ);
                //Log.d("COLLISION", "Character: " + character.positionX + ", " + character.positionY + ", " + character.positionZ);

                if (dx*dx + dy*dy + dz*dz < minDist*minDist && !finalCollision) {
                    onCollision(a);
                    finalCollision = true;
                    Log.d("COLLISION", "Asteroid: " + a.positionX + ", " + a.positionY + ", " + a.positionZ);
                    Log.d("COLLISION", "Character: " + character.positionX + ", " + character.positionY + ", " + character.positionZ);

                    //Log.d("COLLISION", "Collision detected!");
                }
            }
        }
        character.simulate(perSec);
    }

    @Override
    public void draw() {
       GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);

        for (TunnelSegment s : segments) {
            s.bindTexture();
            s.draw();
            for (Asteroid a : s.asteroids) {
                a.bindTexture();
                a.draw();
            }
        }
        if (character != null && character.shown) {
            character.draw();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            float[] orientations   = new float[3];

            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
            SensorManager.getOrientation(rotationMatrix, orientations);

            float roll = orientations[2];   // radians, tilt left/right

            tunnelRotation = roll;

            score += 1;
            updateScoreDisplay();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    public void onCollision(Asteroid asteroid) {
        this.asteroid = asteroid;
        Log.d("COLLISION", "Collision detected!");
        activity.runOnUiThread(() -> ((MainActivity)activity).showGameOver());
        return;
    }

    public void updateScoreDisplay() {
        activity.runOnUiThread(() -> {
            scoreText.setText("Score: " + score);
        });
    }


}



