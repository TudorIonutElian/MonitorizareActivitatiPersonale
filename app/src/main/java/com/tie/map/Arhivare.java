package com.tie.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Arhivare extends AppCompatActivity {
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arhivare);
        setTitle(R.string.titluJson);

        mQueue = Volley.newRequestQueue(this);
        final ArrayList<ActivitatePersonala> activitatePersonalaArrayList = new ArrayList<ActivitatePersonala>();

        String URL = "https://jsonkeeper.com/b/KAOL";
        JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
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

                            ActivitatePersonala activitatePersonala = new ActivitatePersonala(data, kilograme, oresport, oreodihna, calorii, coeficient);
                            activitatePersonalaArrayList.add(activitatePersonala);
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

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(mRequest);

    }

}
