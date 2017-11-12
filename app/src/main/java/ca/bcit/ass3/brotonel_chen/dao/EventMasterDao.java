package ca.bcit.ass3.brotonel_chen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.database.IEventDetail;
import ca.bcit.ass3.brotonel_chen.database.IEventMaster;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 04-Nov-2017.
 */

public class EventMasterDao extends Dao {

    private static final String TAG = EventMasterDao.class.getSimpleName();

    public EventMasterDao(Context context) {
        super(context);
    }

    public long insert(PartyEvent partyEvent) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_NAME_COLUMN, partyEvent.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, partyEvent.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, partyEvent.getTime());

        long result = database.insert(IEventMaster.EVENT_MASTER_TABLE, null, values);

        Log.d(TAG, "Insert " + result + " row");

        return result;
    }

    public ArrayList<PartyEvent> findAllPartyEvents() {
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE, null);
        int count = cursor.getCount();
        if (count == 0) {
            return null;
        }
        ArrayList<PartyEvent> partyEvents = new ArrayList<>(count);
        if (cursor.moveToFirst()) {
            do {
                PartyEvent partyEvent = new PartyEvent();
                partyEvent.setEventId(cursor.getLong(0));
                partyEvent.setName(cursor.getString(1));
                partyEvent.setDate(cursor.getString(2));
                partyEvent.setTime(cursor.getString(3));
                partyEvents.add(partyEvent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return partyEvents;
    }

    public PartyEvent findPartyEvent(String name, String date) {
        PartyEvent partyEvent = null;
        String query = "SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                + " WHERE " + IEventMaster.EVENT_NAME_COLUMN + " = '" + name
                + "' AND " + IEventMaster.EVENT_DATE_COLUMN + " = '" + date + "';";
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                + " WHERE " + IEventMaster.EVENT_NAME_COLUMN + " = '" + name
                + "' AND " + IEventMaster.EVENT_DATE_COLUMN + " = '" + date + "';", null);

        Log.d(TAG, query);
        if (cursor.moveToFirst()) {
            Log.d(TAG, "Record Found!!!");
            partyEvent = new PartyEvent();
            partyEvent.setEventId(cursor.getLong(0));
            partyEvent.setName(cursor.getString(1));
            partyEvent.setDate(cursor.getString(2));
            partyEvent.setTime(cursor.getString(3));
        }

        cursor.close();
        return partyEvent;
    }

    public ArrayList<PartyEvent> findPartyEventsByName(String name) {
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                + " WHERE " + IEventMaster.EVENT_NAME_COLUMN + " = '" + name + "';", null);
        int count = cursor.getCount();
        if (count == 0) {
            return null;
        }

        ArrayList<PartyEvent> partyEvents = new ArrayList<>(count);
        if (cursor.moveToFirst()) {
            do {
                PartyEvent partyEvent = new PartyEvent();
                partyEvent.setEventId(cursor.getLong(0));
                partyEvent.setName(cursor.getString(1));
                partyEvent.setDate(cursor.getString(2));
                partyEvent.setTime(cursor.getString(3));
                partyEvents.add(partyEvent);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return partyEvents;
    }

    public PartyEvent findPartyEventById(long eventId) {
        PartyEvent partyEvent = null;
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventMaster.EVENT_MASTER_TABLE
                + " WHERE " + IEventMaster.EVENT_ID_COLUMN + " = '" + eventId + "';", null);
        if (cursor.moveToFirst()) {
            partyEvent = new PartyEvent();
            partyEvent.setEventId(cursor.getLong(0));
            partyEvent.setName(cursor.getString(1));
            partyEvent.setDate(cursor.getString(2));
            partyEvent.setTime(cursor.getString(3));
        }
        cursor.close();
        return partyEvent;
    }

    public int delete(long eventId) {
        int itemDelete = database.delete(IEventDetail.EVENT_DETAIL_TABLE
                , IEventMaster.EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(eventId)});
        Log.d(TAG, "Delete " + itemDelete + " items");
        int result = database.delete(IEventMaster.EVENT_MASTER_TABLE
                , IEventMaster.EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(eventId)});
        Log.d(TAG, "Delete " + result + " row");
        return result;
    }

    public long update(PartyEvent partyEvent) {
        ContentValues values = new ContentValues();
        values.put(IEventMaster.EVENT_NAME_COLUMN, partyEvent.getName());
        values.put(IEventMaster.EVENT_DATE_COLUMN, partyEvent.getDate());
        values.put(IEventMaster.EVENT_TIME_COLUMN, partyEvent.getTime());

        long result = database.update(IEventMaster.EVENT_MASTER_TABLE, values
                , IEventMaster.EVENT_ID_COLUMN + " = ?", new String[]{String.valueOf(partyEvent.getEventId())});
        Log.d(TAG, "Update " + result + " row");
        return result;
    }
}
