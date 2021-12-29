package com.segunfrancis.payoneerpaymentmethods.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.segunfrancis.payoneerpaymentmethods.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
