package com.example.finalproject;

import gl.modeltypes.ShadedColoredModel;
import gl.ObjectMaker;

public class Character extends ShadedColoredModel {

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

    public float collisionRadius = 0.2f;

    public Character(){

        ObjectMaker om=new ObjectMaker();

        om.pushMatrix();
        om.color(0f,0.5f, 1f);
        om.translate(0, -1.8f, 0);
        om.sphere(1,1,1);
        om.popMatrix();

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
