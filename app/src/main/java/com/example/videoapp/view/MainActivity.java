package com.example.videoapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.videoapp.R;

/**
 * This class is responsible to hold navigation host fragment.
 * Basically this is the base screen and further screens will be hosted on this host.
 *
 * @author Harpal Singh
 * @version 0.0.1
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
