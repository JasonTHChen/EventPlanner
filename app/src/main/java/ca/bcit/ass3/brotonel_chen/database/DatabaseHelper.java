package ca.bcit.ass3.brotonel_chen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.model.Item;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

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
            PartyEvent[] events = new EventSeed().getEvents();
            for (int i = 0; i < events.length; i++) {
                insertSampleEvents(sqLiteDatabase, events[i]);
                if (events[i].getItemCount() > 0) {
                    ArrayList<Item> items = events[i].getItems();
                    for (Item item : items) {
                        insertSampleItems(sqLiteDatabase, item, events[i].getEventId());
                    }
                }
            }
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

    private void insertSampleEvents(SQLiteDatabase db, PartyEvent partyEvent) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_ID_COLUMN, partyEvent.getEventId());
        values.put(IEventMaster.EVENT_NAME_COLUMN, partyEvent.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, partyEvent.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, partyEvent.getTime());

        db.insert(IEventMaster.EVENT_MASTER_TABLE, null, values);
    }

    private void insertSampleItems(SQLiteDatabase db, Item item, long eventId) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getQuantity());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());
        values.put(IEventMaster.EVENT_ID_COLUMN, eventId);

        db.insert(IEventDetail.EVENT_DETAIL_TABLE, null, values);
    }
}
