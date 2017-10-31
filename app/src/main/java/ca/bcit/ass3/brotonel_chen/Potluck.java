package ca.bcit.ass3.brotonel_chen;

/**
 * Created by Jason on 30-Oct-2017.
 */

public class Potluck {
    private String mName;
    private String mDate;
    private String mTime;

    public Potluck(String name, String date, String time) {
        this.mName = name;
        this.mDate = date;
        this.mTime = time;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }
}
