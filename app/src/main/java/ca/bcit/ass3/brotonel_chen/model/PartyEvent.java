package ca.bcit.ass3.brotonel_chen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 03-Nov-2017.
 */

public class PartyEvent {
    private static final String TAG = PartyEvent.class.getSimpleName();

    private long mEventId;
    private String mName;
    private String mDate;
    private String mTime;
    private List<Item>items;

    public PartyEvent() {
        this.mName = "";
        this.mDate = "";
        this.mTime = "";
        this.items = new ArrayList<>();
    }

    public PartyEvent(String name, String date, String time) {
        this.items = new ArrayList<>();
        this.mName = name;
        this.mDate = date;
        this.mTime = time;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public long getEventId() {
        return this.mEventId;
    }

    public void setEventId(long mEventId) {
        this.mEventId = mEventId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getName() {
        return this.mName;
    }

    public String getDate() {
        return this.mDate;
    }

    public String getTime() {
        return this.mTime;
    }
}
