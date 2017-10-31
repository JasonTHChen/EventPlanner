package ca.bcit.ass3.brotonel_chen;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Jason on 30-Oct-2017.
 */

public class PotluckDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "potluck.db";
    private static final int DB_VERSION = 1;
    private Context context;

    private static final Potluck[] POTLUCKS = {
            new Potluck("Halloween Party", "October 30, 2017", "6:30PM"),
            new Potluck("Christmas Party", "December 20, 2017", "12:30PM"),
            new Potluck("New Year Eve", "December 31, 2017", "8:00 PM")
    };

    PotluckDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            if (oldVersion < 1) {
                db.execSQL(getCreateEventMasterTableSql());
                for (Potluck potluck : POTLUCKS) {
                    insertPotluck(db, potluck);
                }
            }
            if (oldVersion < 2)
                db.execSQL("ALTER TABLE COUNTRY ADD COLUMN POPULATION NUMERIC;");
        } catch (SQLException sqlException) {
            String msg = "[PotluckDbHelper / updateMyDatabase/insertPotluck] DB unavailable";
            msg += "\n\n" + sqlException.toString();
            Toast t = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            t.show();
        }
    }

    private String getCreateEventMasterTableSql() {
        String sql = "";
        sql += "CREATE TABLE Event_Master (";
        sql += "_eventId INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "Name TEXT, ";
        sql += "Date TEXT, ";
        sql += "Time TEXT);";

        return sql;
    }

    private void insertPotluck(SQLiteDatabase db, Potluck potluck) {
        ContentValues values = new ContentValues();
        values.put("Name", potluck.getName());
        values.put("Date", potluck.getDate());
        values.put("Time", potluck.getTime());

        db.insert("Event_Master", null, values);
    }
}
