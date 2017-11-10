package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 31-Oct-2017.
 */

public class EventMasterAdapter extends ArrayAdapter<PartyEvent> {
    private static final String TAG = EventMasterAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<PartyEvent> mPartyEvents;
    private ArrayList<PartyEvent> tempEvents;

    public EventMasterAdapter(Context context, ArrayList<PartyEvent> partyEvents) {
        super(context, 0, partyEvents);
        this.mPartyEvents = partyEvents;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final Activity activity = (Activity) mContext;

        PartyEvent partyEvent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_list_row, parent, false);
        }

        TextView eventName = convertView.findViewById(R.id.textView_list_name);
        TextView eventDate = convertView.findViewById(R.id.textView_list_date);
        TextView eventTime = convertView.findViewById(R.id.textView_list_time);

        eventName.setText(partyEvent.getName());
        eventDate.setText(partyEvent.getDate());
        eventTime.setText(partyEvent.getTime());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults filterResults = new FilterResults();
                final ArrayList<PartyEvent> results = new ArrayList<>();
                if (tempEvents == null) {
                    tempEvents = mPartyEvents;
                }
                if (charSequence != null) {
                    if (tempEvents.size() > 0) {
                        for (PartyEvent p : tempEvents) {
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
                mPartyEvents = (ArrayList<PartyEvent>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
