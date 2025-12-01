package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MyRenderer my_renderer;
    TextView scoreText;

    private MediaPlayer soundEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView surfaceView=findViewById(R.id.surfaceView);
        surfaceView.setEGLContextClientVersion(3);
        surfaceView.setEGLConfigChooser(8,8,8,8,16,0);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);

        scoreText = findViewById(R.id.scoreText);

        my_renderer = new MyRenderer(this, scoreText);
        surfaceView.setRenderer(my_renderer);

        soundEffect = MediaPlayer.create(this, R.raw.sound);
    }
    public void showGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.SpacePopupTheme);
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.game_over_popup, null);
        builder.setView(popupView);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button restartBtn = popupView.findViewById(R.id.restartButton);

        restartBtn.setOnClickListener(v -> {
            dialog.dismiss();
            restartGame();
        });
    }

    public void restartGame() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 👉 Screen was tapped
            Globals.screenTapped = true;
            if (soundEffect != null) {
                soundEffect.start();
            }
        }

        // Let the normal event handling continue
        return super.onTouchEvent(event);
    }


    @Override
    public void onPause(){

        my_renderer.pauseCamera();
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        my_renderer.resumeCamera();
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
        if (soundEffect != null) {
            soundEffect.release();
            soundEffect = null;
        }
    }


}
