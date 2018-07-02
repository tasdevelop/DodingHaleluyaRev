package com.tua.bona.dodinghaleluya;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DodingDbHelper extends SQLiteOpenHelper {
    private static String DB_PATH="";
    private static final String DATABASE_NAME = "gkps.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;
    private final Context ctx;


    public DodingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        this.ctx = context;
    }
    void createDataBase() throws IOException {

        if(!checkDataBase()) {
            this.getReadableDatabase();
            copyDataBase();
            this.close();
        }
    }
    private boolean checkDataBase() {
        File DbFile = new File(DB_PATH + DATABASE_NAME);
        return DbFile.exists();
    }
    boolean openDataBase() throws SQLException {
        db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return db != null;
    }
    public synchronized void close(){
        if(db != null)
            db.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void copyDataBase() throws IOException {

        InputStream mInput =  ctx.getAssets().open(DATABASE_NAME);
        Log.d("db pathnya",DB_PATH);
        String outfileName = DB_PATH;
        OutputStream mOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(buffer))>0) {
            mOutput.write(buffer, 0, mLength);
        }
        mOutput.flush();
        mInput.close();
        mOutput.close();
    }




}
