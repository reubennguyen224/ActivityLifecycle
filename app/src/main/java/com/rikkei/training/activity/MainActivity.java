package com.rikkei.training.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnB;
    String tag = "----Activity A----";
    MediaPlayer mediaPlayer;
    int time, timeB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Action A");

        btnB = findViewById(R.id.btnB);

        if (savedInstanceState != null) time = savedInstanceState.getInt("key",0);

        btnB.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, ActivityB.class);

            startActivity(intent);
        });
        prepareMusic();

        Log.d(tag, "onCreate");
        Toast.makeText(this, "ActivityA: onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key", time);
    }

    void prepareMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bai1);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo( time);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
        Toast.makeText(this, "ActivityA: onDestroy", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
        mediaPlayer.release();
        time = 0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
        Toast.makeText(this, "ActivityA: onPause", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
        time = mediaPlayer.getCurrentPosition();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop");
        Toast.makeText(this, "ActivityA: onStop", Toast.LENGTH_SHORT).show();
        time = mediaPlayer.getCurrentPosition();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart");
        Toast.makeText(this, "ActivityA: onStart", Toast.LENGTH_SHORT).show();
        mediaPlayer.seekTo(time);
        mediaPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
        Toast.makeText(this, "ActivityA: onResume", Toast.LENGTH_SHORT).show();
        mediaPlayer.seekTo( time);
        mediaPlayer.start();
    }
}