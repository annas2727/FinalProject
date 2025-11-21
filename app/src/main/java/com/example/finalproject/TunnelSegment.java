package com.example.finalproject;

import gl.ObjectMaker;
import gl.modeltypes.ShadedTexturedModel;
import gl.Texture;
import android.content.Context;


public class TunnelSegment extends ShadedTexturedModel {

    public float z_position;
    private Context context;
    private Texture texture;

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
    }

    public void bindTexture() { //helper to be used in draw
        texture.setActive(0);
    }


    private void area(ObjectMaker om){
        float size=5;
        float width=0.1f;
        om.pushMatrix();
        om.color(0.5f,0.5f,0.5f);

        om.identity();
        om.translate(0,size/2,-size/2);
        om.cylinderX(size,width,width,8);
        om.translate(0,-size,0);
        om.cylinderX(size,width,width,8);
        om.translate(0,0,size);
        om.cylinderX(size,width,width,8);
        om.translate(0,size,0);
        om.cylinderX(size,width,width,8);

        om.identity();
        om.rotateY(90);
        om.translate(0,size/2,-size/2);
        om.cylinderX(size,width,width,8);
        om.translate(0,-size,0);
        om.cylinderX(size,width,width,8);
        om.translate(0,0,size);
        om.cylinderX(size,width,width,8);
        om.translate(0,size,0);
        om.cylinderX(size,width,width,8);

        om.identity();
        om.rotateZ(90);
        om.translate(0,size/2,-size/2);
        om.cylinderX(size,width,width,8);
        om.translate(0,-size,0);
        om.cylinderX(size,width,width,8);
        om.translate(0,0,size);
        om.cylinderX(size,width,width,8);
        om.translate(0,size,0);
        om.cylinderX(size,width,width,8);

        om.popMatrix();
    }

    private void axis(ObjectMaker om){
        float width=0.1f;
        float length=2f;
        om.pushMatrix();
        om.color(0,1,0);
        om.cylinderY(width,length,width,8);
        om.rotateX(90);
        om.color(0,0,1);
        om.cylinderY(width,length,width,8);
        om.rotateZ(90);
        om.color(1,0,0);
        om.cylinderY(width,length,width,8);
        om.popMatrix();
    }

}
