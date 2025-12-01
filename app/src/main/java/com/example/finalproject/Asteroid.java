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

    public float collisionRadius = 0.15f;

    public Asteroid(Context context, float baseX, float baseY, float baseZ) {
        super();

        this.baseX = baseX;
        this.baseY = baseY;
        this.baseZ = baseZ;

        ObjectMaker om = new ObjectMaker();
        om.sphere(0.3f, 0.3f, 0.3f, 10);
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

        float rotatedX = (float)( baseX * Math.cos(tunnelRotation) - baseY * Math.sin(tunnelRotation) );
        float rotatedY = (float)( baseX * Math.sin(tunnelRotation) + baseY * Math.cos(tunnelRotation) );

        positionX = rotatedX;
        positionY = rotatedY;
        positionZ = segmentZ + baseZ;

        localTransform.identity();
        localTransform.rotateZ(tunnelRotation); //rotate asteroids with tunnel
        localTransform.translate(baseX, baseY, segmentZ + baseZ);
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
