package ca.bcit.ass3.brotonel_chen.dao;

import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by woody on 05-Nov-2017.
 */

public class EventMasterValidation {

    public static boolean isValidEvent(PartyEvent partyEvent) {
        if (partyEvent.getName() == null || partyEvent.getName().trim().equals("")) {
            return false;
        } else if (partyEvent.getDate() == null || partyEvent.getDate().trim().equals("")) {
            return false;
        }

        return true;
    }
}
