package com.example.noise_projects;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.util.Date;

public class DetectionFragment extends Fragment {

    private static final int AUDIO_PERMISSION_CODE = 101;

    private TextView tvMainDb, tvCurrentDb, tvPeakDb, btnDetection;
    private CardView cardView21;
    private MediaRecorder mediaRecorder;
    private Handler handler = new Handler();

    private boolean isDetecting = false;
    private int peakDb = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detection, container, false);
        tvMainDb = view.findViewById(R.id.textView42);
        tvCurrentDb = view.findViewById(R.id.tvCurrentDb);
        tvPeakDb = view.findViewById(R.id.tvPeakDb);
        btnDetection = view.findViewById(R.id.dectection_btn);
        cardView21 = view.findViewById(R.id.cardView21);

        btnDetection.setOnClickListener(v -> {
            if (!isDetecting) {
                if (checkPermission()){
                    startDetection();

            } else requestPermission();
            } else {
                stopDetection();
            }
            if(!checkPermission()){
                requestPermission();
            }
        });

        return view;
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
                new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == AUDIO_PERMISSION_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            startDetection();

        } else {
            Toast.makeText(getContext(),
                    "Microphone permission required", Toast.LENGTH_SHORT).show();
        }
    }

    private void startDetection() {
        isDetecting = true;
        peakDb = 0;
        btnDetection.setText("STOP DETECTION");

        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(
                    requireContext().getCacheDir().getAbsolutePath() + "/temp.3gp"
            );
            mediaRecorder.prepare();
            mediaRecorder.start();

            handler.post(updateRunnable);

        } catch (Exception e) {
            e.printStackTrace();
            btnDetection.setText("START DETECTION");
            isDetecting = false;
        }
    }

    private void stopDetection() {
        isDetecting = false;
        btnDetection.setText("START DETECTION");
        handler.removeCallbacks(updateRunnable);

        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {

            if (mediaRecorder != null && isDetecting) {

                int amplitude = mediaRecorder.getMaxAmplitude();
                if (amplitude > 0) {

                    int dbValue = Math.max(0, (int) (20 * Math.log10(amplitude)));
                    if (dbValue > peakDb) peakDb = dbValue;

                    tvMainDb.setText(String.valueOf(dbValue));
                    tvCurrentDb.setText(dbValue + " dB");
                    tvPeakDb.setText(peakDb + " dB");

                    cardView21.setCardBackgroundColor(getGradientColor(30, 100, dbValue));

                }

                handler.postDelayed(this, 500);
            }
        }
    };

    private int getGradientColor(int minDb, int maxDb, int currentDb) {

        int green = 0xFF22C55E;
        int yellow = 0xFFFACC15;
        int red = 0xFFEF4444;

        if (currentDb <= minDb) return green;
        if (currentDb >= maxDb) return red;

        float fraction = (float) (currentDb - minDb) / (maxDb - minDb);
        return fraction <= 0.5f ? blend(green, yellow, fraction / 0.5f)
                : blend(yellow, red, (fraction - 0.5f) / 0.5f);
    }

    private int blend(int from, int to, float ratio) {
        int r = (int) ((Color.red(to) - Color.red(from)) * ratio + Color.red(from));
        int g = (int) ((Color.green(to) - Color.green(from)) * ratio + Color.green(from));
        int b = (int) ((Color.blue(to) - Color.blue(from)) * ratio + Color.blue(from));
        return Color.rgb(r, g, b);
    }
}