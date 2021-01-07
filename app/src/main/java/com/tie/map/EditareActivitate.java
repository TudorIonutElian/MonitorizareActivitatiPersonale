package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditareActivitate extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private ActivitatePersonala activitatePersonala;

    private EditText edit_show_data;
    private EditText edit_show_kilograme;
    private EditText edit_show_oresport;
    private EditText edit_show_oreodihna;
    private EditText edit_show_calorii;
    private EditText edit_show_coeficient;

    private Button btnSalveazaInregistrare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_activitate);
        setTitle("Activitate Editare Inregistrare");
        databaseHelper = new DatabaseHelper(this.getApplicationContext());

        edit_show_data = (EditText) findViewById(R.id.edit_show_data);
        edit_show_kilograme = (EditText) findViewById(R.id.edit_show_kilograme);
        edit_show_oresport = (EditText) findViewById(R.id.edit_show_oresport);
        edit_show_oreodihna = (EditText) findViewById(R.id.edit_show_oreodihna);
        edit_show_calorii = (EditText) findViewById(R.id.edit_show_calorii);
        edit_show_coeficient = (EditText) findViewById(R.id.edit_show_coeficient);

        btnSalveazaInregistrare = (Button) findViewById(R.id.btnSalveazaInregistrare);


        final Intent intent = getIntent();
        final int id_stergere = Integer.parseInt(intent.getStringExtra("id_stergere"));

        Cursor c = databaseHelper.getActivitateById(id_stergere);
        if(c.moveToFirst()){
            edit_show_data.setText(c.getString(1));
            edit_show_kilograme.setText(String.valueOf(c.getInt(2)));
            edit_show_oresport.setText(String.valueOf(c.getInt(3)));
            edit_show_oreodihna.setText(String.valueOf(c.getInt(4)));
            edit_show_calorii.setText(String.valueOf(c.getInt(5)));
            edit_show_coeficient.setText(Double.toString(c.getDouble(6)));
        }

        btnSalveazaInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitatePersonala activitatePersonala = new ActivitatePersonala(edit_show_data.getText().toString(),
                        Integer.parseInt(edit_show_kilograme.getText().toString()),
                        Integer.parseInt(edit_show_oresport.getText().toString()),
                        Integer.parseInt(edit_show_oreodihna.getText().toString()),
                        Integer.parseInt(edit_show_calorii.getText().toString()),
                        Double.parseDouble(edit_show_coeficient.getText().toString())
                );
                if (databaseHelper.updateActivitate(id_stergere, activitatePersonala)) {
                    Toast.makeText(getApplicationContext(), R.string.raspuns_update_inregistrare, Toast.LENGTH_SHORT).show();
                }

            }
        });


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