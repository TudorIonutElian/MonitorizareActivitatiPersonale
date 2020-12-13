package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Identificare elemente pe pagina de pornire
    private Button mBtnStartApp;
    private Button mBtnSetari;
    DatabaseHelper bazadate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.app_name);
        bazadate = new DatabaseHelper(this);

        // Alocare elemente de pe prima pagina
        mBtnStartApp = (Button) findViewById(R.id.btnStart);
        mBtnSetari = (Button) findViewById(R.id.btnSetari);

        // Set onClick pentru buton start
        mBtnStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAplicatieClickActivity();
            }
        });

        mBtnSetari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSetariActivity();
            }
        });
    }

    private void startAplicatieClickActivity(){
        startActivity(new Intent(MainActivity.this, Monitorizare.class));
    }

    private void startSetariActivity(){
        startActivity(new Intent(MainActivity.this, Setari.class));
    }
}
