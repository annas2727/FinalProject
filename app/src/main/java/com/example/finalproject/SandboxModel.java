package com.example.finalproject;

import gl.ObjectMaker;
import gl.modeltypes.ShadedColoredModel;

public class SandboxModel extends ShadedColoredModel {
    void PlotBlaster(ObjectMaker om){
        om.color(1f,1f, 1f);
        om.translate(0.25f,-1.5f,0);
        om.cylinder(0.20f,0.20f,0.20f,8);
        om.color(1,0,0);
        om.translate(0,-0.125f,0);
        om.cylinder(0.15f,0.05f,0.15f,8);
    }

    void PlotWing(ObjectMaker om){
        om.translate(0, -1.45f, 0);
        om.rotateZ(15);
        om.color(1f,1f, 1f);
        om.trapezoid(1.3f, 2.8f, 0.2f, 0.0f, 0.0f);
    }

    void PlotWhiteCylinder(ObjectMaker om){
        //white body
        om.color(1f,1f, 1f);
        om.translate(0.8f,-2.5f,0);
        om.cylinder(0.6f,4,0.6f,8);

        //white cone top
        om.translate(0,2f,0);
        om.cone(0.6f,0.6f,0.6f,8);

        //white cone blaster
        om.translate(0,-4f,0);
        om.cone(1f,1f,1f,8);

        //red cone blaster
        om.color(1,0,0);
        om.translate(0,-0.15f,0);
        om.cone(0.8f,1.2f,0.8f,8);
    }

    public SandboxModel(){

        ObjectMaker om=new ObjectMaker();

        //You can use this space as your sandbox to make your own model.
        //In general you have to repeat the following 3 steps:
        // 1. go to a new location (using translate, rotate, scale, identity),
        // 2. pick a color
        // 3. add a primitive shape block to your model (box, pyramid, trapezoid, cylinder, sphere, etc)

        //The following is just an example:


        //long body
        om.pushMatrix();
        om.color(1f,1f, 1f);
        om.cylinder(0.75f,3,0.75f,8);
        om.popMatrix();

        int blasters = 6;
        for (int i=0;i<blasters;i++){
            om.pushMatrix();
            om.rotateY(i*((float) 360 /blasters));
            //om.translate(0,1,0);
            PlotBlaster(om);
            om.popMatrix();
        }

        //shuttle point
        om.translate(0,1.5f,0);
        om.color(1f,1f, 1f);
        om.cone(0.75f,1.5f,0.75f,8);

        //window
        om.pushMatrix();
        om.translate(0,0.2f,-0.25f);
        om.rotateX(35);
        om.color(0,0,0);
        om.box(0.3f,0.1f,0.2f);
        om.popMatrix();

        //wings
        int wings = 3;
        for (int j=0;j<wings;j++){
            om.pushMatrix();
            om.rotateY(j * 90);
            PlotWing(om);
            om.popMatrix();
        }

        //orange body
        om.pushMatrix();
        om.color(1f,0.5f, 0);
        om.translate(0,-1f,0.8f);
        om.cylinder(1f,4.5f,1f,8);
        om.popMatrix();

        //orange cone
        om.translate(0,1.25f,0.8f);
        om.color(1f,0.5f, 0);
        om.cone(1f,1.25f,1f,8);
        om.popMatrix();

        for (int k=0;k<2;k++) {
            om.pushMatrix();
            om.rotateY(k*180);
            PlotWhiteCylinder(om);
            om.popMatrix();
        }

        //Helpful tips:
        //1.Use the accelerometer to see your model from various angles as you edit it.

        //2.If you want to see where is your current coordinate system use the following line:
        //axis(om);

        //3.If you want to see a 5x5x5 space box use the following line:
        //area(om);

        //4.When you are done please delete the axis or area box.


        //At the end flush all the vertices (XYZ, triangles, etc) as one solid object.
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
