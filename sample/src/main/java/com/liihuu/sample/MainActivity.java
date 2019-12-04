package com.liihuu.sample;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.liihuu.progressbar.CircleProgressBar;
import com.liihuu.progressbar.FloatTextProgressBar;
import com.liihuu.progressbar.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekBar = findViewById(R.id.seekBar);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        final FloatTextProgressBar floatTextProgressBar = findViewById(R.id.floatTextProgressBar);

        final CircleProgressBar circleProgressBar = findViewById(R.id.circleProgressBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(progress);
                floatTextProgressBar.setProgress(progress);
                circleProgressBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
