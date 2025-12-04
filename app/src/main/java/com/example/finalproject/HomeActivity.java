package com.example.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private MediaPlayer spaceAmbience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Button playButton = findViewById(R.id.playButton);

        spaceAmbience = MediaPlayer.create(this, R.raw.ambience);
        spaceAmbience.setLooping(true);

        if (spaceAmbience != null) {
            spaceAmbience.start();
        }

        playButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            spaceAmbience.stop();
        });
    }


    @Override
    public void onDestroy() {

        super.onDestroy();
        if (spaceAmbience != null) {
            spaceAmbience.release();
            spaceAmbience = null;
        }
    }
}