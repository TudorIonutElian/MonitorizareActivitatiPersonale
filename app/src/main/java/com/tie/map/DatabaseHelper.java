package com.tie.map;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "map.db";
    public static final String MAP_TABLE_NAME = "map_data";
    public static final String MAP_COLUMN_ID = "id";
    public static final String MAP_COLUMN_DATE = "data_adaugarii";
    public static final String MAP_COLUMN_KG = "kilograme";
    public static final String MAP_COLUMN_SPORT = "sport";
    public static final String MAP_COLUMN_ODIHNA = "odihna";
    public static final String MAP_COLUMN_CALORII = "calorii";
    public static final String MAP_COLUMN_COEFCIENT = "coeficient";


    private HashMap hp;

    public DatabaseHelper(Context context) {
        super(context, "map.db" , null, 1);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table 'map_data' " +
                        "('id' integer primary key, 'data' String, 'kg' integer, 'sport' integer, 'odihna' integer, 'calorii' integer, 'coeficient' decimal(2,2))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS MAP_TABLE_NAME");
        onCreate(db);
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + MAP_TABLE_NAME , null);
        return res;
    }


    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MAP_TABLE_NAME);
        return numRows;
    }


    public boolean inserareActivitate(ActivitatePersonala activitatePersonala){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentActivitate = new ContentValues();

        contentActivitate.put("data", activitatePersonala.getData_adaugarii());
        contentActivitate.put("kg", activitatePersonala.getNumar_kilograme());
        contentActivitate.put("sport", activitatePersonala.getNumar_ore_sport());
        contentActivitate.put("odihna", activitatePersonala.getNumar_ore_odihna());
        contentActivitate.put("calorii", activitatePersonala.getNumar_calorii_consumate());
        contentActivitate.put("coeficient", activitatePersonala.getValoare_coeficient());
        long rezultat = 0;
        try{
           rezultat = db.insert("map_data", null, contentActivitate);
        }catch (Exception ex){
            Log.d("Excep", ex.getMessage());
        }
        if(rezultat == 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean stergereActivitate(int idStergere){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM 'map_data' WHERE id = "+ idStergere +"");
        db.close();
        return true;
    }
}
