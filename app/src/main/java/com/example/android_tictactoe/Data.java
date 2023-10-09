package com.example.android_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Data extends AppCompatActivity {

    TextView marcadorX, marcadorO;

    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        marcadorO = findViewById(R.id.marcadorO);
        marcadorX = findViewById(R.id.marcadorX);
        btnClose = findViewById(R.id.modalCloseButton);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Data.this, Dashboard.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int ganadoX = bundle.getInt("ganadoX");
            int ganadoO = bundle.getInt("ganadoO");

            marcadorX.setText(String.valueOf(ganadoX));
            marcadorO.setText(String.valueOf(ganadoO));
        }
    }
}