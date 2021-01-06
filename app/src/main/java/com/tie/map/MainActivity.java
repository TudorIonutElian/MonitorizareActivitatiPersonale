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
    private Button mBtnAfisareSetari;

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
        mBtnAfisareSetari = (Button) findViewById(R.id.btnAfisareSetari);

        // Set onClick pentru buton start
        mBtnStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAplicatieClickActivity();
            }
        });

        // Set onClick pentru buton afisare Setari
        mBtnAfisareSetari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAfisareSetariActivity();
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

    private void startAfisareSetariActivity(){
        startActivity(new Intent(MainActivity.this, afisare_setari.class));
    }
}
