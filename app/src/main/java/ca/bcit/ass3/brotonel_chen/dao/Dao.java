package ca.bcit.ass3.brotonel_chen.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ca.bcit.ass3.brotonel_chen.database.DatabaseHelper;

/**
 * Created by Jason on 04-Nov-2017.
 */

public class Dao {

    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public Dao(Context context) {
        this.mContext = context;
    }

    public void open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = DatabaseHelper.getHelper(mContext);
        }

        database = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        dbHelper.close();
        database = null;
    }
}
