package com.example.smartdroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartdroid.MainActivity;
import com.example.smartdroid.R;

public class ConfirmActivity extends AppCompatActivity {

    TextView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        btn = findViewById(R.id.homeBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}