package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Vizualizare extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    // Creare elemente de afisare pe pagina Vizualizare MAP Info
    private Spinner spinner;
    private TextView id_info;
    private TextView data_info;
    private TextView kilograme_info;
    private TextView sport_info;
    private TextView odihna_info;
    private TextView calorii_info;
    private TextView coeficient_info;

    private Button editeazaInregistrare;
    private Button stergeInregistrare;


    private List<ActivitatePersonala> lista_activitati_personale = new ArrayList<ActivitatePersonala>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare);

        data_info                           = (TextView) findViewById(R.id.v_ID_dataInfo);
        kilograme_info                      = (TextView) findViewById(R.id.v_ID_kilogrameInfo);
        sport_info                          = (TextView) findViewById(R.id.v_ID_sportInfo);
        odihna_info                         = (TextView) findViewById(R.id.v_ID_odihnaInfo);
        calorii_info                        = (TextView) findViewById(R.id.v_ID_caloriiInfo);
        coeficient_info                     = (TextView) findViewById(R.id.v_ID_coeficientInfo);

        editeazaInregistrare                = (Button) findViewById(R.id.btnEditeaza);
        stergeInregistrare                  = (Button) findViewById(R.id.btnSterge);

        setTitle(R.string.titleVizualizare);
        databaseHelper = new DatabaseHelper(this.getApplicationContext());

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<Integer> lista_id = new ArrayList<Integer>();
        Cursor c = databaseHelper.getData();
        if(c.moveToFirst()){
            do {
                String data = c.getString(1).toString();
                int kilograme = c.getInt(2);
                int sport = c.getInt(3);
                int odihna = c.getInt(4);
                int calorii = c.getInt(5);
                Double coeficient = c.getDouble(6);

                ActivitatePersonala activitatePersonala = new ActivitatePersonala(data, kilograme, sport, odihna, calorii, coeficient);
                lista_activitati_personale.add(activitatePersonala);
                lista_id.add(c.getInt(0));

                // Creare spinner adapter
                ArrayAdapter spinner_adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lista_id);

                // Setare spinner adaptwer dropdown resources
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Setare adapter pentru spinner adapter
                spinner.setAdapter(spinner_adapter);


            } while (c.moveToNext());
        }

        // Setare activitati on spinner click
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // preluare data info si afisare
                String afisare_data = lista_activitati_personale.get(position).getData_adaugarii();
                data_info.setText(afisare_data);

                // preluare numar de kilograme si afisare
                int afisare_kilograme = lista_activitati_personale.get(position).getNumar_kilograme();
                kilograme_info.setText(String.valueOf(afisare_kilograme));

                // preluare numar de ore sport si afisare
                int afisare_oresport = lista_activitati_personale.get(position).getNumar_ore_sport();
                sport_info.setText(String.valueOf(afisare_oresport));

                // preluare numar de ore odihna si afisare
                int afisare_oreodihna = lista_activitati_personale.get(position).getNumar_ore_odihna();
                odihna_info.setText(String.valueOf(afisare_oreodihna));

                // preluare numar de calorii si afisare
                int afisare_calorii = lista_activitati_personale.get(position).getNumar_calorii_consumate();
                calorii_info.setText(String.valueOf(afisare_calorii));

                // preluare coeficient si afisare
                Double afisare_coeficient = lista_activitati_personale.get(position).getValoare_coeficient();
                coeficient_info.setText(String.valueOf(afisare_coeficient));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Setare Buton Stergere
        stergeInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id_stergere = spinner.getId();
                databaseHelper.stergereActivitate(id_stergere);
                Toast.makeText(getApplicationContext(), "Inregistrarea cu nr. " + id_stergere + " a fost stearsa cu succes!", Toast.LENGTH_LONG).show();
            }
        });

        // Setare Buton Editare
        editeazaInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditareActivitate.class);
                intent.putExtra("id_stergere", String.valueOf(spinner.getSelectedItem()));
                startActivity(intent);
            }
        });
    }
}
