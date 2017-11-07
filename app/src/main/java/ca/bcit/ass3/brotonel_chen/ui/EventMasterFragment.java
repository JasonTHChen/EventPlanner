package ca.bcit.ass3.brotonel_chen.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 06-Nov-2017.
 */

public class EventMasterFragment extends ListFragment {
    private EventMasterDao eventMasterDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventMasterDao = new EventMasterDao(getActivity());
        eventMasterDao.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_master, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<PartyEvent> partyEvents = eventMasterDao.findAllPartyEvents();
        EventMasterAdapter adapter = new EventMasterAdapter(getActivity(), partyEvents);
        this.setListAdapter(adapter);
    }
}
