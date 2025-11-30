package com.example.finalproject;

import android.content.Context;

import gl.ObjectMaker;
import gl.Texture;
import gl.modeltypes.ShadedTexturedModel;
import gl.formats.OBJFile;

public class Asteroid extends ShadedTexturedModel {

    public Asteroid(Context context) {
        super();

        ObjectMaker om = new ObjectMaker();

        //om.readOBJ(context, "asteroid.obj");
        //this.model = om.getModel();


        this.localTransform.identity();
        this.localTransform.scale(0.5f,0,0);
        this.localTransform.updateShader();
    }
}
