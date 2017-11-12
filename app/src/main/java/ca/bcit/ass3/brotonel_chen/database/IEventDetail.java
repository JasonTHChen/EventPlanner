package ca.bcit.ass3.brotonel_chen.database;

/**
 * Created by Jason on 03-Nov-2017.
 */

public interface IEventDetail {
    String EVENT_DETAIL_TABLE = "Event_Detail";
    String DETAIL_ID_COLUMN = "detailId";
    String DETAIL_NAME_COLUMN = "itemName";
    String DETAIL_UNIT_COLUMN = "itemUnit";
    String DETAIL_QUANTITY_COLUMN = "itemQuantity";

    String CREATE_EVENT_DETAIL_TABLE = "CREATE TABLE "
            + EVENT_DETAIL_TABLE + "(" + DETAIL_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DETAIL_NAME_COLUMN + " TEXT, "
            + DETAIL_UNIT_COLUMN + " TEXT, "
            + DETAIL_QUANTITY_COLUMN + " INTEGER, "
            + IEventMaster.EVENT_ID_COLUMN + " INTEGER, "
            + "FOREIGN KEY(" + IEventMaster.EVENT_ID_COLUMN + ") REFERENCES "
            + IEventMaster.EVENT_MASTER_TABLE + "(" + IEventMaster.EVENT_ID_COLUMN + "));";
}
