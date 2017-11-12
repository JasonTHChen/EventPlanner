package ca.bcit.ass3.brotonel_chen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.database.IEventDetail;
import ca.bcit.ass3.brotonel_chen.database.IEventMaster;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 05-Nov-2017.
 */

public class EventDetailDao extends Dao {
    private static final String TAG = EventMasterDao.class.getSimpleName();

    public EventDetailDao(Context context) {
        super(context);
    }

    /**
     * insert
     *
     * @param item
     * @param eventId
     * @return
     */
    public long insert(Item item, long eventId) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getUnit());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());
        values.put(IEventMaster.EVENT_ID_COLUMN, eventId);

        long result = database.insert(IEventDetail.EVENT_DETAIL_TABLE, null, values);
        Log.d(TAG, "insert " + result + " row");
        return result;
    }

    public long update(Item item) {
        ContentValues values = new ContentValues();
        values.put(IEventDetail.DETAIL_NAME_COLUMN, item.getName());
        values.put(IEventDetail.DETAIL_UNIT_COLUMN, item.getUnit());
        values.put(IEventDetail.DETAIL_QUANTITY_COLUMN, item.getQuantity());

        long result = database.update(IEventDetail.EVENT_DETAIL_TABLE, values
                , IEventDetail.DETAIL_ID_COLUMN + " = ?", new String[]{String.valueOf(item.getItemId())});
        Log.d(TAG, "Update " + result + " row");
        return result;
    }

    public Item findItemById(long itemId) {
        Item item = null;
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventDetail.EVENT_DETAIL_TABLE
                + " WHERE " + IEventDetail.DETAIL_ID_COLUMN + " = '" + itemId + "';", null);
        if (cursor.moveToFirst()) {
            item = new Item();
            item.setItemId(cursor.getLong(0));
            item.setName(cursor.getString(1));
            item.setUnit(cursor.getString(2));
            item.setQuantity(cursor.getInt(3));
        }
        cursor.close();
        return item;
    }

    public ArrayList<Item> findItemsByEventId(long eventId) {
        Cursor cursor = database.rawQuery("SELECT DISTINCT * FROM " + IEventDetail.EVENT_DETAIL_TABLE
                + " WHERE " + IEventMaster.EVENT_ID_COLUMN + " = '" + eventId + "'", null);
        int count = cursor.getCount();
        if (count == 0) {
            return null;
        }
        ArrayList<Item> items = new ArrayList<>(count);
        if (cursor.moveToFirst()) {
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
        return items;
    }

    public int delete(long itemId) {
        int result = database.delete(IEventDetail.EVENT_DETAIL_TABLE
                , IEventDetail.DETAIL_ID_COLUMN + " = ?", new String[]{String.valueOf(itemId)});
        Log.d(TAG, "Delete " + result + " row");
        return result;
    }
}
