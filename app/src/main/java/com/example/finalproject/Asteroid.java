package com.example.finalproject;

import android.content.Context;

import java.util.Random;

import gl.ObjectMaker;
import gl.Texture;
import gl.modeltypes.ShadedTexturedModel;

public class Asteroid extends ShadedTexturedModel {

    // position relative to the center of its TunnelSegment
    private float baseX, baseY, baseZ;

    // spin
    private float spinX, spinY, spinZ;
    private float angleX, angleY, angleZ;
    public float positionX, positionY, positionZ;
    float asteroidRadius = 0.3f;

    public float collisionRadius = 0.15f; //0.15

    public Asteroid(Context context, float baseX, float baseY, float baseZ) {
        super();

        this.baseX = baseX;
        this.baseY = baseY;
        this.baseZ = baseZ;

        ObjectMaker om = new ObjectMaker();
        om.sphere(asteroidRadius, asteroidRadius, asteroidRadius, 10);
        om.flush(this);
        this.texture = new Texture(context, "asteroid_texture.png");

        Random r = new Random();

        localTransform.identity();
        localTransform.scale(0.01f, 0.01f, 0.01f);
        localTransform.updateShader();
        spinX = r.nextFloat() * 40f - 20f;
        spinY = r.nextFloat() * 40f - 20f;
        spinZ = r.nextFloat() * 40f - 20f;
    }
    public void update(float dt, float segmentZ, float tunnelRotation) {
        angleX += spinX * dt;
        angleY += spinY * dt;
        angleZ += spinZ * dt;
        float cos = (float) Math.cos(tunnelRotation);
        float sin = (float) Math.sin(tunnelRotation);

        float rotatedX = baseX * cos - baseY * sin;
        float rotatedY = baseX * sin + baseY * cos;
        positionX = rotatedX;
        positionY = rotatedY;
        positionZ = segmentZ + baseZ;

        localTransform.identity();
        localTransform.translate(positionX, positionY, positionZ);
        localTransform.rotate(angleX, 1, 0, 0);
        localTransform.rotate(angleY, 0, 1, 0);
        localTransform.rotate(angleZ, 0, 0, 1);
        localTransform.updateShader();
    }

    public void bindTexture() {
        if (texture != null) {
            texture.setActive(0);
        }
    }
}
