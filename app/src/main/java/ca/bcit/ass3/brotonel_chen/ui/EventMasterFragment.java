package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 06-Nov-2017.
 */

public class EventMasterFragment extends ListFragment {

    OnEventSelectListener mCallback;

    public interface OnEventSelectListener {
        void onEventSelect(long id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_master, container, false);
        updateEventsView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        PartyEvent selectedEvent = (PartyEvent) l.getItemAtPosition(position);
        mCallback.onEventSelect(selectedEvent.getEventId());

        // TODO: for tablet
        //getListView().setItemChecked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnEventSelectListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void updateEventsView() {
        EventMasterDao eventMasterDao = new EventMasterDao(getActivity());
        eventMasterDao.open();
        ArrayList<PartyEvent> partyEvents = eventMasterDao.findAllPartyEvents();
        eventMasterDao.close();
        EventMasterAdapter adapter = new EventMasterAdapter(getActivity(), partyEvents);
        this.setListAdapter(adapter);
    }
}
