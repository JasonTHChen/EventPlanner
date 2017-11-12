package ca.bcit.ass3.brotonel_chen.dao;

import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 05-Nov-2017.
 */

public class EventDetailValidation {

    public static boolean isValidItem(Item item) {
        if (item.getName() == null || item.getName().trim().equals("")) {
            return false;
        }

        return true;
    }
}
