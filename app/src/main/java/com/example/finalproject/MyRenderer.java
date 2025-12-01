package com.example.finalproject;

import android.app.Activity;
import android.opengl.GLES30;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gl.renderers.GyroscopicRenderer;

public class MyRenderer extends GyroscopicRenderer implements View.OnTouchListener {

    List<TunnelSegment> segments = new ArrayList<>();
    float tunnelSpeed = 2f;
    float segmentLength = 3f;
    float segmentCount = 10;
    Activity activity;
    SandboxModel my_sandboxModel;
    SandboxModelCopy my_sandboxModel;

    public MyRenderer(Activity _activity){
        super(_activity);
        this.activity=_activity;
    }


    @Override
    public void setup() {

        for (int i = 0; i < segmentCount; i++) {
            TunnelSegment segment = new TunnelSegment(activity);
            segment.z_position = -i * segmentLength;
            segment.localTransform.translate(0, 0, -5);
            segment.localTransform.updateShader();
            segments.add(segment);
        }
        //In this app we only use 1 model, the SandboxModel.
        //Go to SandboxModel.java to edit its geometry.
        my_sandboxModel =new SandboxModelCopy();
        my_sandboxModel.localTransform.translate(0,0,-5);
        my_sandboxModel.localTransform.updateShader();

        background(1f,1f,1f);//white background
        setLightDir(0,-1,-1);

        setRotationCenter(0,0,-5);
        setFOV(80);

    }


    double lastTime=0;

    @Override
    public void simulate(double elapsedDisplayTime) {

        float perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        for (TunnelSegment s : segments) {
            s.z_position += tunnelSpeed * perSec;

            if (s.z_position > 2f) {
                s.z_position -= segmentCount * segmentLength;
            }
            s.localTransform.identity();
            s.localTransform.translate(0, 0, s.z_position);
            s.localTransform.updateShader();
        }
    }

    @Override
    public void draw() {

        //On every frame this method will be called to draw the scene from the current perspective

        //First we clear the previous frame
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT| GLES30.GL_DEPTH_BUFFER_BIT);

        //And then we draw the model
        for (TunnelSegment s : segments) {
            s.bindTexture();
            s.draw();
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
