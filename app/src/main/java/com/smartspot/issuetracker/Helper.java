package com.smartspot.issuetracker;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Helper extends AppCompatActivity {
    public void makeToast(String message) {
        Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_SHORT).show();
    }
}
