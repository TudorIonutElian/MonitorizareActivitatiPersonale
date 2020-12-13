package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Vizualizare extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    ActivitatePersonala activitatePersonala;

    public int ID;
    public int kilograme;
    public int sport;
    public int odihna;
    public int calorii;
    public double coeficient;
    public Date date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare);

        setTitle(R.string.titleVizualizare);
        databaseHelper = new DatabaseHelper(this.getApplicationContext());

        Cursor cursor = databaseHelper.getData(1);
        if(cursor.moveToFirst() && cursor.getCount() >= 1){
            kilograme = cursor.getInt(2);
            sport = cursor.getInt(3);
            odihna = cursor.getInt(4);
            calorii = cursor.getInt(5);
            coeficient = cursor.getDouble(6);

            String data = cursor.getString(1);
            try {
                date1 = new SimpleDateFormat("dd-M-yyyy").parse(data);

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        activitatePersonala = new ActivitatePersonala(date1, kilograme, sport, odihna, calorii, coeficient);
        Log.d("DB: ", activitatePersonala.toString());


    }
}
