package ca.bcit.ass3.brotonel_chen.database;

import ca.bcit.ass3.brotonel_chen.model.Item;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 06-Nov-2017.
 */

public class EventSeed {

    private PartyEvent[] events;

    public EventSeed() {
        events = new PartyEvent[3];
        events[0] = new PartyEvent("Halloween Party", "October 30, 2017", "6:30PM");
        events[0].setEventId(1);
        events[1] = new PartyEvent("Christmas Party", "December 20, 2017", "12:30PM");
        events[1].setEventId(2);
        events[2] = new PartyEvent("New Year Eve", "December 31, 2017", "8:00 PM");
        events[2].setEventId(3);
        Item[] items = {
                new Item("Coca Cola", "6 packs", 5),
                new Item("Pizza", "Large", 3),
                new Item("Potato Chips", "Large Bag", 5),
                new Item("Napkins", "Pieces", 100)
        };

        events[1].addItems(items);
    }

    public PartyEvent[] getEvents() {
        return events;
    }
}
