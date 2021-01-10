package com.tie.map;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Arhivare extends AppCompatActivity {
    private ArrayList<String> arrayList;
    private ListView listView;
    private TextView textView;
    private ArrayList<ActivitatePersonala> activitatePersonalaArrayList;

    String URL = "https://jsonkeeper.com/b/KAOL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivare);
        setTitle(R.string.titluJson);

        listView = (ListView) findViewById(R.id.listViewZileJSON);
        activitatePersonalaArrayList = new ArrayList<ActivitatePersonala>();
        arrayList = new ArrayList<String>();
        textView = findViewById(R.id.textMesaj);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(URL, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){

                textView.setText("Va rugam asteptati, datele sunt preluate....");
                try {
                    JSONArray jsonArray = response.getJSONArray("activitati");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject activitate = jsonArray.getJSONObject(i);
                        JSONArray anul_curent = activitate.getJSONArray("202" + i);

                        JSONObject anul_curent_ianuarie = anul_curent.getJSONObject(0);
                        JSONArray anul_curent_ianuarie_zile = anul_curent_ianuarie.getJSONArray("ianuarie");

                        // Adaugare zile de ianuarie in lista
                        for(int j = 0; j< anul_curent_ianuarie_zile.length(); j++ ){
                            JSONObject ziua = (JSONObject) anul_curent_ianuarie_zile.get(j);
                            String data             = ziua.getString("data");
                            int kilograme           = ziua.getInt("numarKilograme");
                            int oresport            = ziua.getInt("numarOreSport");
                            int oreodihna           = ziua.getInt("numarOreOdihna");
                            int calorii             = ziua.getInt("numarCalorii");
                            double coeficient       = ziua.getDouble("coeficient");
                            boolean working = false;
                            if(kilograme > Integer.parseInt(Double.toString(coeficient))){
                                working = true;

                            }

                            ActivitatePersonala activitatePersonala = new ActivitatePersonala(data, kilograme, oresport, oreodihna, calorii, coeficient);
                            activitatePersonalaArrayList.add(activitatePersonala);
                            arrayList.add(String.format(
                                    "--- INFO - In data de : %s aveati %d kilograme, ati facut %d ore sport, v-ati odihnit %d ore, ati consumat un numar de %d calorii, iar la finalul zilei aveati un total de %.2f kilograme",
                                    activitatePersonala.getData_adaugarii(),
                                    activitatePersonala.getNumar_kilograme(),
                                    activitatePersonala.getNumar_ore_sport(),
                                    activitatePersonala.getNumar_ore_odihna(),
                                    activitatePersonala.getNumar_calorii_consumate(),
                                    activitatePersonala.getValoare_coeficient()));
                        }

                        JSONObject anul_curent_februarie = anul_curent.getJSONObject(1);
                        JSONArray anul_curent_februarie_zile = anul_curent_februarie.getJSONArray("februarie");

                        // Adaugare zile de februarie in lista
                        for(int j = 0; j< anul_curent_februarie_zile.length(); j++ ){
                            JSONObject ziua = (JSONObject) anul_curent_februarie_zile.get(j);
                            String data             = ziua.getString("data");
                            int kilograme           = ziua.getInt("numarKilograme");
                            int oresport            = ziua.getInt("numarOreSport");
                            int oreodihna           = ziua.getInt("numarOreOdihna");
                            int calorii             = ziua.getInt("numarCalorii");
                            double coeficient       = ziua.getDouble("coeficient");


                            ActivitatePersonala activitatePersonala = new ActivitatePersonala(data, kilograme, oresport, oreodihna, calorii, coeficient);
                            activitatePersonalaArrayList.add(activitatePersonala);
                            arrayList.add(String.format(
                                    "--- INFO - In data de : %s aveati %d kilograme, ati facut %d ore sport, v-ati odihnit %d ore, ati consumat un numar de %d calorii, iar la finalul zilei aveati un total de %.2f kilograme",
                                    activitatePersonala.getData_adaugarii(),
                                    activitatePersonala.getNumar_kilograme(),
                                    activitatePersonala.getNumar_ore_sport(),
                                    activitatePersonala.getNumar_ore_odihna(),
                                    activitatePersonala.getNumar_calorii_consumate(),
                                    activitatePersonala.getValoare_coeficient()));

                        }

                        JSONObject anul_curent_martie = anul_curent.getJSONObject(2);
                        JSONArray anul_curent_martie_zile = anul_curent_martie.getJSONArray("martie");

                        // Adaugare zile de februarie in lista
                        for(int j = 0; j< anul_curent_martie_zile.length(); j++ ){
                            JSONObject ziua = (JSONObject) anul_curent_martie_zile.get(j);
                            String data             = ziua.getString("data");
                            int kilograme           = ziua.getInt("numarKilograme");
                            int oresport            = ziua.getInt("numarOreSport");
                            int oreodihna           = ziua.getInt("numarOreOdihna");
                            int calorii             = ziua.getInt("numarCalorii");
                            double coeficient       = ziua.getDouble("coeficient");


                            ActivitatePersonala activitatePersonala = new ActivitatePersonala(data, kilograme, oresport, oreodihna, calorii, coeficient);
                            activitatePersonalaArrayList.add(activitatePersonala);
                            arrayList.add(String.format(
                                    "--- INFO - In data de : %s aveati %d kilograme, ati facut %d ore sport, v-ati odihnit %d ore, ati consumat un numar de %d calorii, iar la finalul zilei aveati un total de %.2f kilograme",
                                    activitatePersonala.getData_adaugarii(),
                                    activitatePersonala.getNumar_kilograme(),
                                    activitatePersonala.getNumar_ore_sport(),
                                    activitatePersonala.getNumar_ore_odihna(),
                                    activitatePersonala.getNumar_calorii_consumate(),
                                    activitatePersonala.getValoare_coeficient()));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response){
                Log.e("Eroare: ", e.getMessage());
            }
        });
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Arhivare.this, android.R.layout.simple_list_item_1, arrayList);
                textView.setVisibility(View.INVISIBLE);
                listView.setAdapter(arrayAdapter);
            }
        }, 10000);
    }

}