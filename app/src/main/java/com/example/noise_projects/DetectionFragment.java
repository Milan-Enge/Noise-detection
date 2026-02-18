package com.example.noise_projects;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class DetectionFragment extends Fragment {

    private static final int REQUEST_AUDIO_PERMISSION = 200;

    private MediaRecorder mediaRecorder;
    private Handler handler = new Handler();
    private Runnable runnable;

    private TextView tvCurrentDb, tvPeakDb, tvMainDb, startBtn;
    private View circleCard;

    private double peakDb = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detection, container, false);

        tvCurrentDb = view.findViewById(R.id.tvCurrentDb);
        tvPeakDb = view.findViewById(R.id.tvPeakDb);
        tvMainDb = view.findViewById(R.id.textView42);
        startBtn = view.findViewById(R.id.dectection_btn);
        circleCard = view.findViewById(R.id.cardView21);

        startBtn.setOnClickListener(v -> checkPermission());

        return view;
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_AUDIO_PERMISSION
            );

        } else {
            startRecording();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_AUDIO_PERMISSION) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startRecording();

            }
        }
    }

    private void startRecording() {

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile("/dev/null");

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        runnable = new Runnable() {
            @Override
            public void run() {

                if (mediaRecorder != null) {

                    double amplitude = mediaRecorder.getMaxAmplitude();
                    double db = 20 * Math.log10(amplitude);

                    if (db < 0) db = 0;

                    tvMainDb.setText(String.valueOf((int) db));
                    tvCurrentDb.setText((int) db + " dB");

                    if (db > peakDb) {
                        peakDb = db;
                        tvPeakDb.setText((int) peakDb + " dB");
                    }

                    changeColor(db);

                    handler.postDelayed(this, 500);
                }
            }
        };

        handler.post(runnable);
    }

    private void changeColor(double db) {

        if (db < 40) {
            circleCard.setBackgroundColor(Color.parseColor("#2E7D32")); // Green
        }
        else if (db < 70) {
            circleCard.setBackgroundColor(Color.parseColor("#F9A825")); // Yellow
        }
        else {
            circleCard.setBackgroundColor(Color.parseColor("#C62828")); // Red
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}