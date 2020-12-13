package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Adaugare extends AppCompatActivity {

    private TextView dataAdaugarii;
    private TextView kilograme;
    private TextView oreSport;
    private TextView oreOdihna;
    private TextView caloriiConsumate;
    private TextView coeficientDate;
    private TextView mesajMAP;

    public int valoareKilograme = 0;
    public int valoareOreSport = 0;
    public int valoareOreOdihna;
    public int valoareCaloriiConsumate;
    public double valoareCoeficient = 0;

    private Button buttonAdaugare;
    private Button buttonPreluare;

    private DatabaseHelper bazaDate;


    private String shared_preferences;

        /*
    * Setare valori pentru prelucrare coeficient
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare);
        setTitle(R.string.titleAdaugare);

        shared_preferences = getResources().getString(R.string.shared_preferences);

        buttonAdaugare = (Button)findViewById(R.id.btnAdaugareValori);
        buttonAdaugare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaValoriMAP();
            }
        });


        preluareSetariMemorate();

        buttonPreluare = (Button)findViewById(R.id.btnPreluareValoriIeri);
        buttonPreluare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preluareValoriIeri();
            }
        });


        // Preluare Valori existente pe Activity
        this.dataAdaugarii       = (TextView) findViewById(R.id.txtDataValue);
        this.kilograme           = (TextView) findViewById(R.id.txtKilogrameValue);
        this.oreSport            = (TextView) findViewById(R.id.txtOreSporValue);
        this.oreOdihna           = (TextView) findViewById(R.id.txtOreOdihnaValue);
        this.caloriiConsumate    = (TextView) findViewById(R.id.txtCaloriiConsumateValue);
        this.coeficientDate      = (TextView) findViewById(R.id.txtCoeficient);
        this.mesajMAP            = (TextView) findViewById(R.id.textMesajMAP);

        setareDataCurenta();
        this.coeficientDate.setText(Double.toString(valoareCoeficient));
        
        // Actualizare numar de kilograme
        this.kilograme.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            private int numarKilograme;

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                valoareKilograme = 0;
                try {
                    int number = Integer.parseInt(kilograme.getText().toString());

                    // Validare numar kilograme peste 50
                    if(number > 49){
                        // Setare vizibilitate controale

                        valoareKilograme +=  number;
                        actualizareCoeficient();
                    }else{
                        Toast.makeText(getApplicationContext(), "Numarul de Kilograme nu poate fi mai mic de 50", Toast.LENGTH_LONG).show();
                        number = 0;
                        kilograme.setText(Integer.toString(number));

                        number = 0;
                        valoareKilograme +=  number;
                        actualizareCoeficient();
                    }
                }catch (Exception ex){

                }

            }
        });

        // Actualizare ore de sport
        this.oreSport.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            private int numarOre;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                valoareOreSport = 0;
                try {
                    int ore = Integer.parseInt(oreSport.getText().toString());
                    valoareOreSport +=  ore;
                    actualizareCoeficient();
                }catch (Exception ex){

                }
            }
        });

        this.oreOdihna.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            private int numarOreOdihna;
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                valoareOreOdihna = 0;
                try {
                    int numarOreOdihna = Integer.parseInt(oreOdihna.getText().toString());
                    valoareOreOdihna +=  numarOreOdihna;
                    actualizareCoeficient();
                }catch (Exception ex){

                }
            }
        });

        this.caloriiConsumate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                valoareCaloriiConsumate = 0;
                try {
                    int numarCaloriiConsumate = Integer.parseInt(caloriiConsumate.getText().toString());
                    valoareCaloriiConsumate += numarCaloriiConsumate;
                    actualizareCoeficient();
                }catch (Exception ex){

                }

            }
        });
    }
    public void preluareValoriIeri(){
        Toast.makeText(getApplicationContext(), "Au fost preluate valorile introduse ieri! Actualizarea se va face automat", Toast.LENGTH_LONG).show();
        SharedPreferences preferences = getSharedPreferences(shared_preferences, MODE_PRIVATE);

        kilograme.setText(String.valueOf(preferences.getInt("numar_kilograme", 0)));
        valoareKilograme = preferences.getInt("numar_kilograme", 0);

        oreSport.setText(String.valueOf(preferences.getInt("numar_ore_sport", 0)));
        valoareOreSport = preferences.getInt("numar_ore_sport", 0);

        oreOdihna.setText(String.valueOf(preferences.getInt("numar_ore_odihna", 0)));
        valoareOreOdihna = preferences.getInt("numar_ore_odihna", 0);

        caloriiConsumate.setText(String.valueOf(preferences.getInt("numar_calorii", 0)));
        valoareCaloriiConsumate = preferences.getInt("numar_calorii", 0);

        actualizareCoeficient();
    }

    public void salveazaValoriMAP(){
        SharedPreferences preferences = getSharedPreferences(shared_preferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        boolean salvezKilograme = preferences.getBoolean("salveazaKilograme", false);
        if(salvezKilograme == true){
            editor.putInt("numar_kilograme", valoareKilograme);
        }else{
            editor.putInt("numar_kilograme", 0);
        }

        boolean salvezOreSport = preferences.getBoolean("salveazaSport", false);
        if(salvezOreSport == true){
            editor.putInt("numar_ore_sport", valoareOreSport);
        }else{
            editor.putInt("numar_ore_sport", 0);
        }

        boolean salvezOreOdihna = preferences.getBoolean("salveazaOdihna", false);
        if(salvezOreOdihna == true){
            editor.putInt("numar_ore_odihna", valoareOreOdihna);
        }else{
            editor.putInt("numar_ore_odihna", 0);
        }

        boolean salvezCalorii = preferences.getBoolean("salveazaCalorii", false);
        if(salvezCalorii == true){
            editor.putInt("numar_calorii", valoareCaloriiConsumate);
        }else{
            editor.putInt("numar_calorii", 0);
        }

        editor.commit();
        Toast.makeText(getApplicationContext(), "Valorile au fost salvate", Toast.LENGTH_LONG).show();
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        ActivitatePersonala activitatePersonala = new ActivitatePersonala(c, valoareKilograme, valoareOreSport, valoareOreOdihna, valoareCaloriiConsumate, valoareCoeficient);

        DatabaseHelper myDbHelper = new DatabaseHelper(this.getApplicationContext());
        myDbHelper.inserareActivitate(activitatePersonala);
    }
    /* Functie pentru actualizare coeficient */

    public void actualizareCoeficient(){
        // actualizare dupa numarul de kilograme modificat
        double coeficientDupaKilograme = valoareKilograme;
        double coeficientDupaOreSport = coeficientDupaKilograme - ((coeficientDupaKilograme * (double) valoareOreSport)/500);

        // actualizare dupa numarul de ore de sport modificat
        this.valoareCoeficient = coeficientDupaOreSport;
        this.coeficientDate.setText(Double.toString(valoareCoeficient));

        // actualizare dupa numarul de ore de odihna modificat
        double coeficientDupaOreOdihna = coeficientDupaOreSport - ((coeficientDupaOreSport * (double) valoareOreOdihna) / 100);
        this.coeficientDate.setText(Double.toString(coeficientDupaOreOdihna));

        // actualizare dupa numarul de calorii consumate
        double coeficientFinal = 0;
        if(valoareCaloriiConsumate > 0 && valoareCaloriiConsumate < 1500){
            coeficientFinal = coeficientDupaOreOdihna - ((coeficientDupaOreOdihna * (double) valoareCaloriiConsumate)/30000);
            this.coeficientDate.setText(Double.toString(coeficientFinal));
        }else if(valoareCaloriiConsumate > 0 && valoareCaloriiConsumate > 1501){
            coeficientFinal = coeficientDupaOreOdihna + ((coeficientDupaOreOdihna * (double) valoareCaloriiConsumate)/30000);
            this.coeficientDate.setText(Double.toString(coeficientFinal));
        }

        int kilogrameInitial = valoareKilograme;
        int kilogrameFinal = (int) coeficientFinal;
        if(kilogrameFinal > kilogrameInitial){
            mesajMAP.setText("Trebuie sa slabesti!");
            mesajMAP.setTextColor(ContextCompat.getColor(this, R.color.colorRed));
        }else{
            mesajMAP.setText("Programul functioneaza!");
            mesajMAP.setTextColor(ContextCompat.getColor(this, R.color.colorGreen));
        }

    }

    /*
    * Functie pentru setarea corecta a datei
    * Nu permite utilizatorului sa seteze o data incorecta
    */

    public void setareDataCurenta(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        this.dataAdaugarii.setText(formattedDate);
        this.dataAdaugarii.setFocusable(false);
    }

    public void preluareSetariMemorate(){
        SharedPreferences preferences = getSharedPreferences(shared_preferences, MODE_PRIVATE);
        boolean setareKilograme = preferences.getBoolean("salveazaKilograme", false);
        boolean setareSport = preferences.getBoolean("salveazaSport", false);
        boolean setareOdihna = preferences.getBoolean("salveazaOdihna", false);
        boolean setareCalorii = preferences.getBoolean("salveazaCalorii", false);
    }


}