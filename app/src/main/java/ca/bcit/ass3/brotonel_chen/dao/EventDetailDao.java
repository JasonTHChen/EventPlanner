package ca.bcit.ass3.brotonel_chen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.ass3.brotonel_chen.database.IEventDetail;
import ca.bcit.ass3.brotonel_chen.database.IEventMaster;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * EventDetailDao class is a Data Access Object of Event_Detail.
 * Accesses and modifies data from the Event_Detail table.
 *
 * Created by Jason on 05-Nov-2017.
 */

public class EventDetailDao extends Dao {
    private static final String TAG = EventDetailDao.class.getSimpleName();

    /**
     * Inherit Dao constructor.
     *
     * @param context - current context.
     */
    public EventDetailDao(Context context) {
        super(context, IEventDetail.EVENT_DETAIL_TABLE);
    }

    /**
     * Insert an item into certain event by event ID.
     * @param item - item object.
     * @param eventId - event ID.
     */
    public void insert(Item item, long eventId) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getUnit());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());
        values.put(IEventMaster.EVENT_ID_COLUMN, eventId);
        Log.i(TAG, "Inserting item");
        super.insert(values);
    }

    /**
     * Update an item.
     *
     * @param item - item object.
     */
    public void update(Item item) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getUnit());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());
        String[] args = {String.valueOf(item.getItemId())};
        Log.i(TAG, "Updating item");
        super.update(IEventDetail.DETAIL_ID_COLUMN, args, values);
    }

    /**
     * Delete an item.
     *
     * @param itemId - item ID.
     */
    public void delete(long itemId) {
        String[] args = {String.valueOf(itemId)};
        Log.i(TAG, "Deleting item");
        super.delete(IEventDetail.DETAIL_ID_COLUMN, args);
    }

    /**
     * Find item by the item ID
     *
     * @param itemId - id of an item.
     * @return item - an item if it is found.
     */
    public Item findItemById(long itemId) {
        Item item = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + IEventDetail.EVENT_DETAIL_TABLE
                    + " WHERE " + IEventDetail.DETAIL_ID_COLUMN + " = '" + itemId + "';", null);
            Log.d(TAG, "Found item " + cursor.getCount() + " row");
            if (cursor.moveToFirst()) {
                item = new Item();
                item.setItemId(cursor.getLong(0));
                item.setName(cursor.getString(1));
                item.setUnit(cursor.getString(2));
                item.setQuantity(cursor.getInt(3));
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return item;
    }

    /**
     * Find all the items by the event ID.
     *
     * @param eventId - id of an event.
     * @return items - a list of items if it's found.
     */
    public List<Item> findItemsByEventId(long eventId) {
        List<Item> items = null;
        try {
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT DISTINCT * FROM " + IEventDetail.EVENT_DETAIL_TABLE
                    + " WHERE " + IEventMaster.EVENT_ID_COLUMN + " = '" + eventId + "'", null);
            int count = cursor.getCount();
            Log.d(TAG, "Found items " + count + " rows");
            if (count > 0 && cursor.moveToFirst()) {
                items = new ArrayList<>(count);
                do {
                    Item item = new Item();
                    item.setItemId(cursor.getLong(0));
                    item.setName(cursor.getString(1));
                    item.setUnit(cursor.getString(2));
                    item.setQuantity(cursor.getInt(3));
                    items.add(item);
                } while (cursor.moveToNext());
            }
            cursor.close();
            sqLiteDatabase.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        }
        return items;
    }
}
