package com.example.finalproject;

import gl.ObjectMaker;
import gl.modeltypes.ShadedColoredModel;

public class SandboxModelCopy extends ShadedColoredModel {

    public boolean shown=true;

    public float segments=0;

    public float positionX=0;
    public float positionY=0;
    public float positionZ=0;

    public float speedX=0;
    public float speedY=0;
    public float speedZ=0;

    public float accelarationX=0;
    public float accelarationY=0;
    public float accelarationZ=0;

    public float angleX=0;
    public float angleZ=0;


    void PlotWing(ObjectMaker om){
        om.translate(0, -1.45f, 0);
        om.rotateZ(15);
        om.color(1f,1f, 1f);
        om.trapezoid(1.3f, 2.8f, 0.2f, 0.0f, 0.0f);
    }

    public SandboxModelCopy(){

        ObjectMaker om=new ObjectMaker();

        //You can use this space as your sandbox to make your own model.
        //In general you have to repeat the following 3 steps:
        // 1. go to a new location (using translate, rotate, scale, identity),
        // 2. pick a color
        // 3. add a primitive shape block to your model (box, pyramid, trapezoid, cylinder, sphere, etc)

        //The following is just an example:


        //long body
        om.pushMatrix();
        om.color(0f,0.5f, 1f);
        om.translate(0, -3f, 0);
        om.sphere(1,1,1);
        om.popMatrix();



        //shuttle point

//        int wings = 3;
//        for (int j=0;j<wings;j++){
//            om.pushMatrix();
//            om.rotateY(j * 90);
//            PlotWing(om);
//            om.popMatrix();
//        }

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

    public void simulate(float perSec){

//        angleX+=20*perSec;
//        angleZ+=20*perSec;

        if (Globals.screenTapped == true){
            speedY = 1;
            accelarationY = 1;
            Globals.screenTapped = false;
        }
//        speedX+=accelarationX*perSec;
        speedY-=accelarationY*perSec;
//        speedZ+=accelarationZ*perSec;

        positionX+=speedX*perSec;
        positionY+=speedY*perSec;
        positionZ+=speedZ*perSec;

        if (positionY <= 0f && speedY < 0f) {
            positionY = 0f;
            speedY = 0f;
            accelarationY = 0f;
        }

        if(positionZ>-2){
            positionZ=-2-segments*4;
            shown=true;
        }


        localTransform.reset();
        localTransform.translate(positionX,positionY,positionZ);
        localTransform.rotateX(angleX);
        localTransform.rotateZ(angleZ);
        localTransform.scale(0.5f,0.5f,0.5f);
        localTransform.updateShader();
    }

}
