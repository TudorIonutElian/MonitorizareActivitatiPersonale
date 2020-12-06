package com.tie.map;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DBSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "map.db";
    public static final String MAP_TABLE_NAME = "map_table";
    public static final String MAP_COLUMN_ID = "id";
    public static final String MAP_COLUMN_DATE = "data_adaugarii";
    public static final String MAP_COLUMN_KG = "kilograme";
    public static final String MAP_COLUMN_SPORT = "sport";
    public static final String MAP_COLUMN_ODIHNA = "odihna";
    public static final String MAP_COLUMN_CALORII = "calorii";
    public static final String MAP_COLUMN_COEFCIENT = "coeficient";


    private HashMap hp;

    public DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public DBSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table MAP_TABLE_NAME " +
                        "(MAP_COLUMN_ID integer primary key, MAP_COLUMN_DATE date, MAP_COLUMN_KG integer, MAP_COLUMN_SPORT integer, MAP_COLUMN_ODIHNA integer, MAP_COLUMN_CALORII integer, MAP_COLUMN_COEFCIENT decimal(2,2))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS MAP_TABLE_NAME");
        onCreate(db);
    }

    public boolean insertInregistrare (String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert(MAP_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MAP_TABLE_NAME);
        return numRows;
    }

    public boolean updateAdaugare (Integer id, Date data_adugare, Integer kilograme, Integer sport, Integer odihna, Integer calorii, Double coeficient) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAP_COLUMN_DATE, String.valueOf(data_adugare));
        contentValues.put(MAP_COLUMN_KG, kilograme);
        contentValues.put(MAP_COLUMN_SPORT, sport);
        contentValues.put(MAP_COLUMN_ODIHNA, odihna);
        contentValues.put(MAP_COLUMN_CALORII, calorii);
        contentValues.put(MAP_COLUMN_COEFCIENT, coeficient);

        db.update(MAP_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MAP_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> preluareToateRanduri() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from MAP_TABLE_NAME", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(MAP_COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}
