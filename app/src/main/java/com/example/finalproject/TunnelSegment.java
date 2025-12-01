package com.example.finalproject;

import gl.modeltypes.ShadedTexturedModel;
import gl.Texture;
import gl.ObjectMaker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TunnelSegment extends ShadedTexturedModel {

    public float z_position;
    public List<Asteroid> asteroids = new ArrayList<>();
    private Context context;
    private Texture texture;
    private Random random = new Random();

    public TunnelSegment(Context _context) { //creates tunnel segment
        this.context = _context;

        texture = new Texture(context, "galaxy_background.jpg");

        ObjectMaker om = new ObjectMaker();
        float radius = 1.2f;
        int tunnelSidesCount = 8;

        om.pushMatrix();

        for (int i = 0; i < tunnelSidesCount; i++) {
            float angle = i * (360f / tunnelSidesCount);

            om.pushMatrix();
            om.rotateZ(angle);
            om.translate(radius, 0, 0);
            om.rotateZ(90);
            om.rotateX(90);
            om.color(1, 1, 1);
            om.rectangle(1.1f, 3f);

            om.popMatrix();
        }
        om.popMatrix();
        om.flush(this);
        createAsteroids();
    }
    private void createAsteroids() {
        int count = 1; //int count = 3 + random.nextInt(3); // 3–5 asteroids per segment

        for (int i = 0; i < count; i++) {
            // random angle around tunnel center
            float angleDeg = random.nextFloat() * 360f;
            float angleRad = (float) Math.toRadians(angleDeg);

            // slightly inside tunnel wall
            float r = 0.9f;
            float x = (float) Math.cos(angleRad) * r;
            float y = (float) Math.sin(angleRad) * r;

            float localZ = 0f;

            Asteroid a = new Asteroid(context, x, y, localZ);
            asteroids.add(a);
        }
    }

    public void bindTexture() { //helper to be used in draw
        texture.setActive(0);
    }
}
