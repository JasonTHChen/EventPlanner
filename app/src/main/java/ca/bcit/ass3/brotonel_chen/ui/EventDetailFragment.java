package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventDetailDao;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 07-Nov-2017.
 */

public class EventDetailFragment extends ListFragment {

    private long eventId;
    ItemSelectListener mCallback;

    interface ItemSelectListener {
        void onItemSelect(long id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        eventId = getArguments().getLong("eventId");
        System.out.println(eventId);
        final Button addButton = view.findViewById(R.id.button_detail_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddDetailActivity.class);
                i.putExtra("mode", 0);
                i.putExtra("eventId", eventId);
                getActivity().startActivityForResult(i, 1);
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Item selectedItem = (Item) l.getItemAtPosition(position);
        mCallback.onItemSelect(selectedItem.getItemId());

        // TODO: for tablet
        //getListView().setItemChecked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (ItemSelectListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
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
