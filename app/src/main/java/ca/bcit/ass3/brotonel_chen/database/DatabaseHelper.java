package ca.bcit.ass3.brotonel_chen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import ca.bcit.ass3.brotonel_chen.model.Event;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * DatabaseHelper class creates SQLite connection.
 * Initializes database tables.
 *
 * Created by Jason on 03-Nov-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "event.db";

    private static DatabaseHelper instance;

    /**
     * Inherit SQLiteOpenHelper constructor.
     *
     * @param context - current context.
     */
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Start database instance.
     *
     * @param context - current context.
     * @return databaseHelper.
     */
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    /**
     * Create database tables and insert sample data.
     *
     * @param sqLiteDatabase - SQLite connection.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            // create Event_Master table
            sqLiteDatabase.execSQL(IEventMaster.CREATE_EVENT_MASTER_TABLE);
            // create Event_Detail table
            sqLiteDatabase.execSQL(IEventDetail.CREATE_EVENT_DETAIL_TABLE);
            // insert sample events and items
            Event[] events = new EventSeed().getEvents();
            for (Event event : events) {
                insertSampleEvents(sqLiteDatabase, event);
                if (event.getItemCount() > 0) {
                    List<Item> items = event.getItems();
                    for (Item item : items) {
                        insertSampleItems(sqLiteDatabase, item, event.getEventId());
                    }
                }
            }
        } catch (SQLException e) {
            Log.d(TAG, " Error create tables " + e.getMessage());
        }
    }

    /**
     * Upgrade database version.
     *
     * @param sqLiteDatabase - SQLite connection.
     * @param oldVersion - current version.
     * @param newVersion - upgraded version.
     */
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

    /**
     * Insert event samples.
     *
     * @param sqLiteDatabase - SQLite connection.
     * @param event - party event object.
     */
    private void insertSampleEvents(SQLiteDatabase sqLiteDatabase, Event event) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_ID_COLUMN, event.getEventId());
        values.put(IEventMaster.EVENT_NAME_COLUMN, event.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, event.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, event.getTime());

        long result = sqLiteDatabase.insert(IEventMaster.EVENT_MASTER_TABLE, null, values);
        Log.d(TAG, "insert sample event " + result + " row");
    }

    /**
     * Insert items samples.
     *
     * @param sqLiteDatabase - SQLite connection
     * @param item - item object
     * @param eventId - event id which is associated with the item
     */
    private void insertSampleItems(SQLiteDatabase sqLiteDatabase, Item item, long eventId) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getQuantity());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());
        values.put(IEventMaster.EVENT_ID_COLUMN, eventId);

        long result = sqLiteDatabase.insert(IEventDetail.EVENT_DETAIL_TABLE, null, values);
        Log.d(TAG, "insert sample item " + result + " row");
    }
}
