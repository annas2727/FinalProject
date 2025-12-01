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

    public float collisionRadius = 0.2f; //0.2

    public Character(){

        ObjectMaker om=new ObjectMaker();

        om.pushMatrix();
        om.color(0f,0.5f, 1f);
        om.translate(0, 0, 0);
        om.sphere(1,1,1);
        om.popMatrix();

        om.flush(this);

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

        if (positionY <= -1.0f && speedY < 0f) {
            positionY = -1.0f;
            speedY = 0f;
            accelarationY = 0f;
        }

        positionZ = -2f;

        localTransform.reset();
        localTransform.translate(positionX,positionY,positionZ);
        localTransform.rotateX(angleX);
        localTransform.rotateZ(angleZ);
        localTransform.scale(0.5f,0.5f,0.5f);
        localTransform.updateShader();
    }
}

