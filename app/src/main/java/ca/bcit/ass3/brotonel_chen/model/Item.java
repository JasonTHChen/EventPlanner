package ca.bcit.ass3.brotonel_chen.model;

/**
 * Created by Jason on 03-Nov-2017.
 */

public class Item {
    private long mItemId;
    private String mName;
    private String mUnit;
    private int mQuantity;

    public Item() {

    }

    public Item(String name, String unit, int quantity) {
        this.mName = name;
        this.mUnit = unit;
        this.mQuantity = quantity;
    }

    public long getItemId() {
        return mItemId;
    }

    public void setItemId(long itemId) {
        this.mItemId = itemId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }
}
