package ca.bcit.ass3.brotonel_chen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.ass3.brotonel_chen.database.IEventDetail;
import ca.bcit.ass3.brotonel_chen.database.IEventMaster;
import ca.bcit.ass3.brotonel_chen.model.Event;

/**
 * Data Access Object of Event_Master.
 * Access and modify data from the Event_Master table.
 *
 * Created by Jason on 04-Nov-2017.
 */

public class EventMasterDao extends Dao {

    private static final String TAG = EventMasterDao.class.getSimpleName();

    /**
     * Inherit Dao constructor
     *
     * @param context - current context
     */
    public EventMasterDao(Context context) {
        super(context, IEventMaster.EVENT_MASTER_TABLE);
    }

    /**
     * Insert an event.
     *
     * @param event - event object.
     */
    public void insert(Event event) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_NAME_COLUMN, event.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, event.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, event.getTime());
        Log.i(TAG, "Inserting event");
        super.insert(values);
    }

    /**
     * Update an event.
     *
     * @param event - event object.
     */
    public void update(Event event) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_NAME_COLUMN, event.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, event.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, event.getTime());
        String[] args = {String.valueOf(event.getEventId())};
        Log.i(TAG, "Updating event");
        super.update(IEventMaster.EVENT_ID_COLUMN, args, values);
    }

    /**
     * Delete an event.
     *
     * @param eventId - event ID.
     */
    public void delete(long eventId) {
        String[] args = {String.valueOf(eventId)};
        Log.i(TAG, "Deleting event");
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.delete(IEventDetail.EVENT_DETAIL_TABLE
                    , IEventMaster.EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(eventId)});
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        super.delete(IEventMaster.EVENT_ID_COLUMN, args);
    }


    /**
     * Find all events.
     *
     * @return events - a list of event if it's found.
     */
    public List<Event> findAllEvents() {
        List<Event> events = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE, null);
            int count = cursor.getCount();
            Log.d(TAG, "Found events " + count + " row");
            if (count > 0 && cursor.moveToFirst()) {
                events = new ArrayList<>(count);
                do {
                    Event event = new Event();
                    event.setEventId(cursor.getLong(0));
                    event.setName(cursor.getString(1));
                    event.setDate(cursor.getString(2));
                    event.setTime(cursor.getString(3));
                    events.add(event);
                } while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return events;
    }

    /**
     * Find all events by event name.
     *
     * @param name - name of event.
     * @return a list of events if it is found.
     */
    public List<Event> findEventsByName(String name) {
        List<Event> events = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                    + " WHERE " + IEventMaster.EVENT_NAME_COLUMN + " = '" + name + "';", null);
            int count = cursor.getCount();
            Log.d(TAG, "Found events " + count + " rows");
            if (count > 0 && cursor.moveToFirst()) {
                events = new ArrayList<>(count);
                do {
                    Event event = new Event();
                    event.setEventId(cursor.getLong(0));
                    event.setName(cursor.getString(1));
                    event.setDate(cursor.getString(2));
                    event.setTime(cursor.getString(3));
                    events.add(event);
                } while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return events;
    }

    /**
     * Find an event by event ID.
     *
     * @param eventId - event ID.
     * @return an event if it is found.
     */
    public Event findEventById(long eventId) {
        Event event = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                    + " WHERE " + IEventMaster.EVENT_ID_COLUMN + " = '" + eventId + "';", null);
            if (cursor.moveToFirst()) {
                event = new Event();
                event.setEventId(cursor.getLong(0));
                event.setName(cursor.getString(1));
                event.setDate(cursor.getString(2));
                event.setTime(cursor.getString(3));
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLiteException e) {
            Log.e(TAG, e.getMessage());
        }
        return event;
    }

    /**
     * search all events by event name.
     *
     * @param name - name of event.
     * @return a list of events if it is found.
     */
    public List<Event> searchEventsByName(String name) {
        List<Event> events = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                    + " WHERE " + IEventMaster.EVENT_NAME_COLUMN + " LIKE '%" + name + "%';", null);
            int count = cursor.getCount();
            Log.d(TAG, "Found events " + count + " rows");
            if (count > 0 && cursor.moveToFirst()) {
                events = new ArrayList<>(count);
                do {
                    Event event = new Event();
                    event.setEventId(cursor.getLong(0));
                    event.setName(cursor.getString(1));
                    event.setDate(cursor.getString(2));
                    event.setTime(cursor.getString(3));
                    events.add(event);
                } while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return events;
    }
}
