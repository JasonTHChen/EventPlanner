package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by woody on 31-Oct-2017.
 */

public class EventMasterAdapter extends ArrayAdapter<PartyEvent> {
    private Context mContext;
    private ArrayList<PartyEvent> mPartyEvents;

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

}
