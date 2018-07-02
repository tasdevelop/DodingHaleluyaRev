package com.tua.bona.dodinghaleluya;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class DodingDbQuery {
    private DodingDbHelper dbHelper;
    private SQLiteDatabase db;

    public DodingDbQuery(Context context) {
        dbHelper = new DodingDbHelper(context);
    }

    public DodingDbQuery createDatabase() throws SQLException {
        try {
            dbHelper.createDataBase();
        } catch (IOException ignored) {
        }
        return this;
    }

    public DodingDbQuery open() throws SQLException {
        try {
//            Log.i("DatabaseNya",
//                    "New database is being copied to device!");
            dbHelper.openDataBase();
            dbHelper.close();
            db = dbHelper.getReadableDatabase();
        } catch (SQLException ignored) {
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Doding> getAllDoding(String sql){

//        String sql="SELECT * FROM " + DodingContract.DodingTable.TBL_NAME;

        Cursor c= db.rawQuery( sql,null);
        ArrayList<Doding> dodingList = new ArrayList<>();
        try {
            if(c.moveToFirst()) {
                do{
                    Doding doding = new Doding();
                    doding.setNo(c.getInt(c.getColumnIndex(DodingContract.DodingTable.COLUMN_NO)));
                    doding.setJudul(c.getString(c.getColumnIndex(DodingContract.DodingTable.COLUMN_JUDUL)));
                    doding.setKategori(c.getString(c.getColumnIndex(DodingContract.DodingTable.COLUMN_KATEGORI)));
                    doding.setLirik(c.getString(c.getColumnIndex(DodingContract.DodingTable.COLUMN_LIRIK)));
                    dodingList.add(doding);
                }while(c.moveToNext());
            }
            return dodingList;
        }catch (Exception ignored){

        }finally {
            if (c != null) {
                c.close();
            }
        }
        return null;




    }
}
