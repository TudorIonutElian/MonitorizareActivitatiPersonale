package com.tie.map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "map.db";
    public static final String MAP_TABLE_NAME = "map_data";
    public static final String MAP_TABLE_NAME_WORKING = "map_data_WORKING";
    public static final String MAP_COLUMN_ID = "id";
    public static final String MAP_COLUMN_DATE = "data";
    public static final String MAP_COLUMN_KG = "kg";

    public static final String MAP_COLUMN_SPORT = "sport";
    public static final String MAP_COLUMN_ODIHNA = "odihna";
    public static final String MAP_COLUMN_CALORII = "calorii";
    public static final String MAP_COLUMN_COEFCIENT = "coeficient";


    private HashMap hp;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + MAP_TABLE_NAME +
                        " (" +
                        MAP_COLUMN_ID + " integer primary key, " +
                        MAP_COLUMN_DATE + " String, " +
                        MAP_COLUMN_KG + " integer, " +
                        MAP_COLUMN_SPORT + " integer, " +
                        MAP_COLUMN_ODIHNA + " integer, " +
                        MAP_COLUMN_CALORII + " integer, " +
                        MAP_COLUMN_COEFCIENT + " decimal(2,2)" +")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + MAP_TABLE_NAME );
        onCreate(db);
    }

    // Preluare Toate inregistrarile
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + MAP_TABLE_NAME, null);
        return res;
    }

    // Get numar inregistrari
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MAP_TABLE_NAME);
        return numRows;
    }

    // Preluare Date activitate personala by ID
    public Cursor getActivitateById(int id_editare){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + MAP_TABLE_NAME + " WHERE " + MAP_COLUMN_ID + " = " + id_editare,
                null);
        return res;
    }

    // Inserare activitate noua
    public boolean inserareActivitate(ActivitatePersonala activitatePersonala){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentActivitate = new ContentValues();

        contentActivitate.put(MAP_COLUMN_DATE, activitatePersonala.getData_adaugarii());
        contentActivitate.put(MAP_COLUMN_KG, activitatePersonala.getNumar_kilograme());
        contentActivitate.put(MAP_COLUMN_SPORT, activitatePersonala.getNumar_ore_sport());
        contentActivitate.put(MAP_COLUMN_ODIHNA, activitatePersonala.getNumar_ore_odihna());
        contentActivitate.put(MAP_COLUMN_CALORII, activitatePersonala.getNumar_calorii_consumate());
        contentActivitate.put(MAP_COLUMN_COEFCIENT, activitatePersonala.getValoare_coeficient());
        long rezultat = 0;
        try{
           rezultat = db.insert(MAP_TABLE_NAME, null, contentActivitate);
        }catch (Exception ex){
            Log.d("Excep", ex.getMessage());
        }
        if(rezultat == 0){
            return true;
        }else{
            return false;
        }

    }
    // Stergere activitate by ID
    public boolean stergereActivitate(int idStergere){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + MAP_TABLE_NAME + " WHERE " + MAP_COLUMN_ID + " = "+ idStergere +"");
        db.close();
        return true;
    }

    // Update actualizare
    public boolean updateActivitate(int id_editare, ActivitatePersonala activitatePersonala){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataUpdate = new ContentValues();

        dataUpdate.put(MAP_COLUMN_DATE, activitatePersonala.getData_adaugarii());
        dataUpdate.put(MAP_COLUMN_KG, activitatePersonala.getNumar_kilograme());
        dataUpdate.put(MAP_COLUMN_SPORT, activitatePersonala.getNumar_ore_sport());
        dataUpdate.put(MAP_COLUMN_ODIHNA, activitatePersonala.getNumar_ore_odihna());
        dataUpdate.put(MAP_COLUMN_CALORII, activitatePersonala.getNumar_calorii_consumate());
        dataUpdate.put(MAP_COLUMN_COEFCIENT, activitatePersonala.getValoare_coeficient());
        db.update(MAP_TABLE_NAME, dataUpdate, MAP_COLUMN_ID + "= ?", new String[]{String.valueOf(id_editare)});

        db.close();
        return true;
    }
}
