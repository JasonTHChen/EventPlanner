package ca.bcit.ass3.brotonel_chen.database;

/**
 * Interface for Event_Detail table.
 *
 * Created by Jason on 03-Nov-2017.
 */

public interface IEventMaster {
    String EVENT_MASTER_TABLE = "Event_Master";
    String EVENT_ID_COLUMN = "eventId";
    String EVENT_NAME_COLUMN = "name";
    String EVENT_DATE_COLUMN = "date";
    String EVENT_TIME_COLUMN = "time";

    String CREATE_EVENT_MASTER_TABLE = "CREATE TABLE "
            + EVENT_MASTER_TABLE + "(" + EVENT_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EVENT_NAME_COLUMN + " TEXT, "
            + EVENT_DATE_COLUMN + " TEXT, "
            + EVENT_TIME_COLUMN + " TEXT);";
}
