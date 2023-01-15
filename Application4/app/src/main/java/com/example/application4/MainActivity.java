package com.example.application4;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable rocketAnimation;
    private ImageView img;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.imageView);
        img.setBackgroundResource(R.drawable.loading_animator);
        rocketAnimation = (AnimationDrawable) img.getBackground();


        Button buttonStart = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar2);


        buttonStart.setOnClickListener(view -> start());
        Button buttonStop = findViewById(R.id.button2);
        buttonStop.setOnClickListener(view -> stop());
    }

    private void start() {
        stop();
        int currentProgress = 9500;
        progressBar.setMax(100);
        ObjectAnimator animator =
                ObjectAnimator.ofInt(progressBar, "progress", currentProgress);
        animator.setDuration(90000);
        animator.start();

        rocketAnimation.start();
    }

    private void stop() {
        progressBar.setMax(0);
        ObjectAnimator.ofInt(progressBar, "progress", 0).start();
        rocketAnimation.stop();
    }
}