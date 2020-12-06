package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Monitorizare extends AppCompatActivity {

    private Button mBtnAdaugareMAP;
    private Button mBtnVizualizareMAP;
    private Button mBtnArhivareMAP;
    private Button mBtnImagineMAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitorizare);
        setTitle(R.string.titleMonitorizare);


        mBtnAdaugareMAP = findViewById(R.id.btnAdaugareMAP);
        mBtnVizualizareMAP = findViewById(R.id.btnVizualizareMAP);
        mBtnArhivareMAP = findViewById(R.id.btnArhivareMAP);
        mBtnImagineMAP = findViewById(R.id.btnImagineMAP);

        mBtnAdaugareMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAdaugareActivity();
            }
        });

        mBtnVizualizareMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVizualizareActivity();
            }
        });

        mBtnArhivareMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startArhivareActivity();
            }
        });

        mBtnImagineMAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImagineActivity();
            }
        });
    }


    private void startAdaugareActivity(){
        startActivity(new Intent(getApplicationContext(), Adaugare.class));
    }

    private void startVizualizareActivity(){
        startActivity(new Intent(getApplicationContext(), Vizualizare.class));
    }

    private void startArhivareActivity(){
        startActivity(new Intent(getApplicationContext(), Arhivare.class));
    }

    private void startImagineActivity(){
        startActivity(new Intent(getApplicationContext(), Imagine.class));
    }
}
