package com.rikkei.training.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.IOException;

public class ActivityB extends AppCompatActivity {

    Button btnA;
    String tag = "----Activity B----";
    MediaPlayer mediaPlayer;
    long time, timeA;
    Bundle bundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Action B");

        btnA = findViewById(R.id.btnA);

        Intent in = getIntent();
        timeA = in.getLongExtra("keyA", 0);
        time = in.getLongExtra("keyB", 0);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ActivityB.this, MainActivity.class);
                intent.putExtra("keyB", mediaPlayer.getCurrentPosition());
                intent.putExtra("keyA", timeA);
                startActivity(intent);
            }
        });



        prepareMusic();


        Log.d(tag, "onCreate");
        Toast.makeText(this, "ActivityB: onCreate", Toast.LENGTH_SHORT).show();
    }



    void prepareMusic(){
        mediaPlayer = MediaPlayer.create(this, R.raw.bai2);
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo((int) time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
        Toast.makeText(this, "ActivityB: onDestroy", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
        mediaPlayer.release();
        time = 0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop");
        Toast.makeText(this, "ActivityB: onStop", Toast.LENGTH_SHORT).show();
        time = mediaPlayer.getCurrentPosition();
        //mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
        Toast.makeText(this, "ActivityB: onPause", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
        time = mediaPlayer.getCurrentPosition();
        bundle = new Bundle();
        bundle.putLong("keyTimeB", time);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "onStart");
        Toast.makeText(this, "ActivityB: onStart", Toast.LENGTH_SHORT).show();
        mediaPlayer.seekTo((int) time);
        mediaPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
        Toast.makeText(this, "ActivityB: onResume", Toast.LENGTH_SHORT).show();
        mediaPlayer.seekTo((int) time);
        mediaPlayer.start();
    }

}