package ca.bcit.ass3.brotonel_chen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

import static ca.bcit.ass3.brotonel_chen.R.raw.potluck;

/**
 * Created by woody on 31-Oct-2017.
 */

public class EventMasterAdapter extends ArrayAdapter<PartyEvent> {
    private Context mContext;

    public EventMasterAdapter(Context context, ArrayList<PartyEvent> potlucks) {
        super(context, 0, potlucks);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final Activity activity = (Activity) mContext;

        PartyEvent partyEvent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
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
