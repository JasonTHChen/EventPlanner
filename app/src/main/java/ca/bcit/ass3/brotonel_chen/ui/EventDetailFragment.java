package ca.bcit.ass3.brotonel_chen.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventDetailDao;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 07-Nov-2017.
 */

public class EventDetailFragment extends ListFragment {

    private long eventId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        eventId = getArguments().getLong("eventId");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventDetailDao eventDetailDao = new EventDetailDao(getActivity());
        eventDetailDao.open();
        ArrayList<Item> items = eventDetailDao.findItemsByEventId(eventId);
        eventDetailDao.close();
        if (items != null) {
            EventDetailAdapter adapter = new EventDetailAdapter(getActivity(), items);
            this.setListAdapter(adapter);
        }
    }
}
