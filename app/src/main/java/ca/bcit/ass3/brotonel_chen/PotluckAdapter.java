package ca.bcit.ass3.brotonel_chen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by woody on 31-Oct-2017.
 */

public class PotluckAdapter extends ArrayAdapter<Potluck> {
    private Context mContext;

    public PotluckAdapter(Context context, ArrayList<Potluck> potlucks) {
        super(context, 0, potlucks);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //final Activity activity = (Activity) mContext;

        Potluck potluck = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        TextView eventName = convertView.findViewById(R.id.textView_list_name);
        TextView eventDate = convertView.findViewById(R.id.textView_list_date);
        TextView eventTime = convertView.findViewById(R.id.textView_list_time);

        eventName.setText(potluck.getName());
        eventDate.setText(potluck.getDate());
        eventTime.setText(potluck.getTime());

        return convertView;
    }

}
