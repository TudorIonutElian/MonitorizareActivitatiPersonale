package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class EditareActivitate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_activitate);
        setTitle("Activitate Editare Inregistrare");

        Intent intent = getIntent();
        int id_stergere = Integer.parseInt(intent.getStringExtra("id_stergere"));
        Log.d("ID", id_stergere + "");

        /*
        *  afisare ce inregistrare dorim sa editam
        *  creare campuri
        *  preluare valori
        *  creare functie de update
        *  returnare la activitatea de vizualizare
        *
        *
        * */
    }
}