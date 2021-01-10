package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Setari extends AppCompatActivity {

    // Create switch
    private Switch salveazaNumarKilograme;
    private Switch salveazaNumarOreSport;
    private Switch salveazaNumarOreOdihna;
    private Switch salveazaNumarCalorii;

    // Creare Butoane

    private Button butonSalvareSetari;
    private Button butonResetareSetari;

    // Creare valori boolean

    private Boolean booleanSwitchKilograme;
    private Boolean booleanSwitchOreSport;
    private Boolean booleanSwitchOreOdihna;
    private Boolean booleanSwitchCalorii;

    public static final String SALVEAZA_KILOGRAME = "salveazaKilograme";
    public static final String SALVEAZA_SPORT = "salveazaSport";
    public static final String SALVEAZA_ODIHNA = "salveazaOdihna";
    public static final String SALVEAZA_CALORII = "salveazaCalorii";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setari);
        setTitle(R.string.titleSetari);


        salveazaNumarKilograme = (Switch) findViewById(R.id.swithcKG);
        salveazaNumarOreSport = (Switch) findViewById(R.id.switchOreSport);
        salveazaNumarOreOdihna = (Switch) findViewById(R.id.switchOreOdihna);
        salveazaNumarCalorii = (Switch) findViewById(R.id.switchCalorii);

        this.butonSalvareSetari = (Button) findViewById(R.id.btnSalvareSetari);
        this.butonResetareSetari = (Button) findViewById(R.id.btnReseteazaValori);

        this.butonSalvareSetari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaSetariCaPreferinte();
            }
        });
        this.butonResetareSetari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salveazaNumarKilograme.setChecked(false);
                salveazaNumarOreSport.setChecked(false);
                salveazaNumarOreOdihna.setChecked(false);
                salveazaNumarCalorii.setChecked(false);
            }
        });
        preluareSetariPreferinte();
        // Setare numar de kilograme
        salveazaNumarKilograme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanSwitchKilograme = salveazaNumarKilograme.isChecked();
                if(booleanSwitchKilograme){
                    salveazaNumarKilograme.setText("DA");
                    salveazaNumarKilograme.setTextColor(getResources().getColor(R.color.colorGreen));
                }else {
                    salveazaNumarKilograme.setText("NU");
                    salveazaNumarKilograme.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        });

        // Setare numar de ore de sport
        salveazaNumarOreSport.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanSwitchOreSport = salveazaNumarOreSport.isChecked();
                if(booleanSwitchOreSport){
                    salveazaNumarOreSport.setText("DA");
                    salveazaNumarOreSport.setTextColor(getResources().getColor(R.color.colorGreen));
                }else {
                    salveazaNumarOreSport.setText("NU");
                    salveazaNumarOreSport.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        });

        // Setare numar de ore de odihna
        salveazaNumarOreOdihna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanSwitchOreOdihna = salveazaNumarOreOdihna.isChecked();
                if(booleanSwitchOreOdihna){
                    salveazaNumarOreOdihna.setText("DA");
                    salveazaNumarOreOdihna.setTextColor(getResources().getColor(R.color.colorGreen));
                }else {
                    salveazaNumarOreOdihna.setText("NU");
                    salveazaNumarOreOdihna.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        });

        // Setare numar de calorii
        salveazaNumarCalorii.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleanSwitchCalorii = salveazaNumarCalorii.isChecked();
                if(booleanSwitchCalorii){
                    salveazaNumarCalorii.setText("DA");
                    salveazaNumarCalorii.setTextColor(getResources().getColor(R.color.colorGreen));
                }else {
                    salveazaNumarCalorii.setText("NU");
                    salveazaNumarCalorii.setTextColor(getResources().getColor(R.color.colorRed));
                }
            }
        });
    }

/* ------ FUNCTIE PENTRU SALVARE SETARILOR IN FISIERUL DE PREFERINTE ------ */
    public void salveazaSetariCaPreferinte(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SALVEAZA_KILOGRAME, salveazaNumarKilograme.isChecked());
        editor.putBoolean(SALVEAZA_SPORT, salveazaNumarOreSport.isChecked());
        editor.putBoolean(SALVEAZA_ODIHNA, salveazaNumarOreOdihna.isChecked());
        editor.putBoolean(SALVEAZA_CALORII, salveazaNumarCalorii.isChecked());

        editor.commit();
        this.finish();
        Toast.makeText(getApplicationContext(), getText(R.string.preferences_setari_salvate), Toast.LENGTH_LONG).show();
    }

/* ------ FUNCTIE PENTRU PRELUAREA SETARILOR DIN FISIERUL DE PREFERINTE ------ */
    public void preluareSetariPreferinte(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        // Setare Kilograme
        boolean setareKilograme = sharedPreferences.getBoolean(SALVEAZA_KILOGRAME, false);
        if(setareKilograme){
            salveazaNumarKilograme.setText("DA");
            salveazaNumarKilograme.setTextColor(getResources().getColor(R.color.colorGreen));
        }else {
            salveazaNumarKilograme.setText("NU");
            salveazaNumarKilograme.setTextColor(getResources().getColor(R.color.colorRed));
        }
        salveazaNumarKilograme.setChecked(setareKilograme);

        // Setare sport
        boolean setareSport = sharedPreferences.getBoolean(SALVEAZA_SPORT, false);
        if(setareSport){
            salveazaNumarOreSport.setText("DA");
            salveazaNumarOreSport.setTextColor(getResources().getColor(R.color.colorGreen));
        }else {
            salveazaNumarOreSport.setText("NU");
            salveazaNumarOreSport.setTextColor(getResources().getColor(R.color.colorRed));
        }
        salveazaNumarOreSport.setChecked(setareSport);

        // Setare odihna
        boolean setareOdihna = sharedPreferences.getBoolean(SALVEAZA_ODIHNA, false);
        if(setareOdihna){
            salveazaNumarOreOdihna.setText("DA");
            salveazaNumarOreOdihna.setTextColor(getResources().getColor(R.color.colorGreen));
        }else {
            salveazaNumarOreOdihna.setText("NU");
            salveazaNumarOreOdihna.setTextColor(getResources().getColor(R.color.colorRed));
        }
        salveazaNumarOreOdihna.setChecked(setareOdihna);

        // Setare calorii
        boolean setareCalorii = sharedPreferences.getBoolean(SALVEAZA_CALORII, false);
        if(setareCalorii){
            salveazaNumarCalorii.setText("DA");
            salveazaNumarCalorii.setTextColor(getResources().getColor(R.color.colorGreen));
        }else {
            salveazaNumarCalorii.setText("NU");
            salveazaNumarCalorii.setTextColor(getResources().getColor(R.color.colorRed));
        }
        salveazaNumarCalorii.setChecked(setareCalorii);


    }
}
