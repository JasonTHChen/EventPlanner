package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.Dao;
import ca.bcit.ass3.brotonel_chen.dao.EventDetailDao;
import ca.bcit.ass3.brotonel_chen.model.Item;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Jason on 07-Nov-2017.
 */

public class EventDetailFragment extends ListFragment {
    private static final String TAG = EventDetailFragment.class.getSimpleName();

    private long eventId;
    EventDetailDao eventDetail;
    ItemSelectListener mCallback;

    interface ItemSelectListener {
        void onItemSelect(long id);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventDetail = new EventDetailDao(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        eventId = getArguments().getLong("eventId");
        final Button addButton = view.findViewById(R.id.button_detail_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddDetailActivity.class);
                i.putExtra("mode", 0);
                i.putExtra("eventId", eventId);
                EventDetailFragment.this.startActivityForResult(i, 1);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.onResume();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Database instances: " + Dao.getCount());
        Bundle args = getArguments();
        if (args != null) {
            updateDetailView(args.getLong("eventId"));
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Item selectedItem = (Item) l.getItemAtPosition(position);
        mCallback.onItemSelect(selectedItem.getItemId());
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
    public void onDestroy() {
        super.onDestroy();
        eventDetail.close();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void updateDetailView(long eventId) {
        this.eventId = eventId;
        List<Item> items = eventDetail.findItemsByEventId(eventId);
        if (items != null) {
            EventDetailAdapter adapter = new EventDetailAdapter(getActivity(), items);
            this.setListAdapter(adapter);
        }
    }
}
