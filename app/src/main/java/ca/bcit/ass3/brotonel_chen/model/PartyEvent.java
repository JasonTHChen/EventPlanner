package ca.bcit.ass3.brotonel_chen.model;

import java.util.ArrayList;

/**
 * Created by Jason on 03-Nov-2017.
 */

public class PartyEvent {
    private static final String TAG = PartyEvent.class.getSimpleName();

    private long mEventId;
    private String mName;
    private String mDate;
    private String mTime;
    private ArrayList<Item> mItems;

    public PartyEvent() {
        this.mName = "";
        this.mDate = "";
        this.mTime = "";
        this.mItems = new ArrayList<>();
    }

    public PartyEvent(String name, String date, String time) {
        this.mItems = new ArrayList<>();
        this.mName = name;
        this.mDate = date;
        this.mTime = time;
    }

    public void addItems(Item[] items) {
        for (int i = 0 ; i < items.length; i++) {
            this.mItems.add(items[i]);
        }
    }

    public void addItem(Item item) {
        this.mItems.add(item);
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public ArrayList<Item> getItems() {
        return mItems;
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
