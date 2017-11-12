package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.model.Event;

/**
 * Created by Jason on 31-Oct-2017.
 */

public class EventMasterAdapter extends ArrayAdapter<Event> {
    private static final String TAG = EventMasterAdapter.class.getSimpleName();
    private Context mContext;
    private List<Event> mEvents;
    private List<Event> tempEvents;

    public EventMasterAdapter(Context context, List<Event> events) {
        super(context, 0, events);
        this.mEvents = events;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final Activity activity = (Activity) mContext;

        Event event = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_row, parent, false);
        }

        TextView eventName = convertView.findViewById(R.id.textView_list_name);
        TextView eventDate = convertView.findViewById(R.id.textView_list_date);
        TextView eventTime = convertView.findViewById(R.id.textView_list_time);

        eventName.setText(event.getName());
        eventDate.setText(event.getDate());
        eventTime.setText(event.getTime());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults filterResults = new FilterResults();
                final ArrayList<Event> results = new ArrayList<>();
                if (tempEvents == null) {
                    tempEvents = mEvents;
                }
                if (charSequence != null) {
                    if (tempEvents.size() > 0) {
                        for (Event p : tempEvents) {
                            if (p.getName().toLowerCase().contains(charSequence.toString())) {
                                results.add(p);
                            }
                        }
                        filterResults.values = results;
                    }
                }
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mEvents = (ArrayList<Event>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
