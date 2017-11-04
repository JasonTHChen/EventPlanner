package ca.bcit.ass3.brotonel_chen.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jason on 03-Nov-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "event.db";

    private static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(IEventMaster.CREATE_EVENT_MASTER_TABLE);
            sqLiteDatabase.execSQL(IEventDetail.CREATE_EVENT_DETAIL_TABLE);
        } catch (SQLException e) {
            Log.d(TAG, " Error create tables " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            try {
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IEventDetail.EVENT_DETAIL_TABLE);
                sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IEventMaster.EVENT_MASTER_TABLE);
                this.onCreate(sqLiteDatabase);
            } catch (SQLException e) {
                Log.d(TAG, " Error drop tables " + e.getMessage());
            }
        }
    }
}
