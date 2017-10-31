package ca.bcit.ass3.brotonel_chen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    //private ArrayList<Potluck potlucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView eventList = (ListView) findViewById(R.id.listView_main_eventList);

        ArrayList<Potluck> potlucks = getPotlucks();

        PotluckAdapter adapter = new PotluckAdapter(MainActivity.this, potlucks);

        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventNames);
        eventList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    private ArrayList<Potluck> getPotlucks() {
        SQLiteOpenHelper helper = new PotluckDbHelper(this);
        ArrayList<Potluck> potlucks = null;

        try {
            db = helper.getReadableDatabase();
            cursor = db.rawQuery("SELECT DISTINCT * FROM Event_Master", null);
            int count = cursor.getCount();
            potlucks = new ArrayList<>(count);

            if (cursor.moveToFirst()) {
                // index = 0;
                do {
                    Potluck potluck = new Potluck(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                    potlucks.add(potluck);
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException sqlException) {
            String msg = "[MainActivity / getEventNames] DB unavailable";
            msg += "\n\n" + sqlException.toString();

            Toast t = Toast.makeText(this, msg, Toast.LENGTH_LONG);
            t.show();
        }

        return potlucks;
    }
}
