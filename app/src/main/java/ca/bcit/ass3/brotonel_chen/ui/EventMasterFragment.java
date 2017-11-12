package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.Dao;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.Event;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Jason on 06-Nov-2017.
 */

public class EventMasterFragment extends ListFragment {
    private static final String TAG = EventMasterFragment.class.getSimpleName();

    OnEventSelectListener mCallback;

    EventMasterDao eventMaster;

    interface OnEventSelectListener {
        void onEventSelect(long id, boolean state);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventMaster = new EventMasterDao(getActivity());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {
                Event selectedEvent = (Event) adapterView.getItemAtPosition(position);
                EventMasterAdapter adapter = (EventMasterAdapter) getListAdapter();
                //adapter.remove(selectedEvent);
                //adapter.notifyDataSetChanged();
                EventOptionDialog dialog = EventOptionDialog.getInstance(selectedEvent.getEventId());
                dialog.show(getFragmentManager(), "event_options_dialog");
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_master, container, false);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Event selectedEvent = (Event) l.getItemAtPosition(position);
        mCallback.onEventSelect(selectedEvent.getEventId(), true);

        // for tablet, highlight selected event
        getListView().setItemChecked(position, true);
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
        eventMaster.close();
        eventMaster = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Database instances: " + Dao.getCount());
        updateEventsView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.onResume();
            }
        }
    }

    /**
     * Update event list view
     */
    public void updateEventsView() {
        List<Event> events = eventMaster.findAllEvents();
        if (events != null) {
            EventMasterAdapter adapter = new EventMasterAdapter(getActivity(), events);
            this.setListAdapter(adapter);
        }
    }
}
