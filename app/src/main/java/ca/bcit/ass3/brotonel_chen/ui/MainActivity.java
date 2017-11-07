package ca.bcit.ass3.brotonel_chen.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ca.bcit.ass3.brotonel_chen.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        eventMasterDao = new EventMasterDao(this);

        eventMasterDao.open();

        PartyEvent[] EVENTS = {
                new PartyEvent("Halloween Party", "October 30, 2017", "6:30PM"),
                new PartyEvent("Christmas Party", "December 20, 2017", "12:30PM"),
                new PartyEvent("New Year Eve", "December 31, 2017", "8:00 PM")
        };

        for (PartyEvent event : EVENTS) {
            if (EventMasterValidation.isValidEvent(event)) {
                eventMasterDao.insert(event);
            }
        }

        System.out.println("-------------------");

        final ArrayList<PartyEvent> partyEvents = eventMasterDao.findAllPartyEvents();

        for (PartyEvent event : partyEvents) {
            System.out.println(event.getName());
        }
*/
        //ListView eventList = (ListView) findViewById(R.id.listView_main_eventList);
        //EventMasterAdapter adapter = new EventMasterAdapter(MainActivity.this, partyEvents);
        //eventList.setAdapter(adapter);
/*
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PartyEvent selectedEvent = (PartyEvent) adapterView.getItemAtPosition(i);
                //PartyEvent p = eventMasterDao.findPartyEventById(selectedEvent.getEventId());
                eventMasterDao.delete(selectedEvent);
                //System.out.println(p.getName());
            }
        });
        */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //eventMasterDao.close();
    }
}
