package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Jason on 06-Nov-2017.
 */

public class EventMasterFragment extends ListFragment {

    OnEventSelectListener mCallback;

    interface OnEventSelectListener {
        void onEventSelect(long id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_master, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateEventsView();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(this).attach(this).commit();
            }
        }
    }

    /**
     * Update event list view
     */
    public void updateEventsView() {
        EventMasterDao eventMasterDao = new EventMasterDao(getActivity());
        eventMasterDao.open();
        ArrayList<PartyEvent> partyEvents = eventMasterDao.findAllPartyEvents();
        eventMasterDao.close();
        if (partyEvents != null) {
            EventMasterAdapter adapter = new EventMasterAdapter(getActivity(), partyEvents);
            this.setListAdapter(adapter);
        }
    }
}
