package ca.bcit.ass3.brotonel_chen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

public class MainActivity extends AppCompatActivity {
    private EventMasterDao eventMasterDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventMasterDao = new EventMasterDao(this);

        eventMasterDao.open();

        PartyEvent[] EVENTS = {
                new PartyEvent("Halloween Party", "October 30, 2017", "6:30PM"),
                new PartyEvent("Christmas Party", "December 20, 2017", "12:30PM"),
                new PartyEvent("New Year Eve", "December 31, 2017", "8:00 PM")
        };

        for (PartyEvent event : EVENTS) {
            //System.out.println(event.getName());
            eventMasterDao.insert(event);
        }

        System.out.println("-------------------");

        ArrayList<PartyEvent> partyEvents = eventMasterDao.findAllPartyEvents();

        for (PartyEvent event : partyEvents) {
            System.out.println(event.getName());
        }

        ListView eventList = (ListView) findViewById(R.id.listView_main_eventList);
        EventMasterAdapter adapter = new EventMasterAdapter(MainActivity.this, partyEvents);

        eventList.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventMasterDao.close();
    }
}
