package ca.bcit.ass3.brotonel_chen.dao;

import ca.bcit.ass3.brotonel_chen.model.Event;

/**
 * Created by Jason on 05-Nov-2017.
 */

public class EventMasterValidation {

    public static boolean isValidEvent(Event event) {
        if (event.getName() == null || event.getName().trim().equals("")) {
            return false;
        } else if (event.getDate() == null || event.getDate().trim().equals("")) {
            return false;
        }

        return true;
    }
}
