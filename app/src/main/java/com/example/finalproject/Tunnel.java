package com.example.finalproject;

import gl.ObjectMaker;
import gl.modeltypes.ShadedColoredModel;

public class Tunnel extends ShadedColoredModel {

    ObjectMaker om = new ObjectMaker();
    int tunnelSidesCount = 8;
    int tunnelSegmentsCount = 10;

    void PlotTunnelSegment(float z_offset){ //creates tunnel segment
        om.pushMatrix();
        om.translate(0, 0, z_offset);

        for (int i = 0; i < tunnelSidesCount; i++) {

            om.pushMatrix();
            om.rotateZ(i * (360f /tunnelSidesCount));

            om.color(1f,1f, 1f);
            om.box(0.75f,0.1f,3);

            om.popMatrix();
        }
        om.popMatrix();
    }

    void PlotTunnels() { //plots tunnel segments
        for (int i = 0; i < tunnelSegmentsCount; i++) {
            float z = -i * 3f;
            PlotTunnelSegment(z);
        }
    }
    public Tunnel(){

        PlotTunnels();
        om.flush(this);
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
