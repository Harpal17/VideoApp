package com.example.videoapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.videoapp.R;

/**
 * This class is responsible for displaying a splash screen on app launch,
 * and launching the Home Screen.
 *
 * @author Harpal Singh
 * @version 0.0.1
 */
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);
        navigateToHomeScreen();
    }

    /**
     * This method will navigate from Splash Screen to Home screen after 1.5 seconds.
     */
    private void navigateToHomeScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}
