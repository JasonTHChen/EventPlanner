package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 07-Nov-2017.
 */

public class EventDetailAdapter extends ArrayAdapter<Item> {
    private Context mContext;

    public EventDetailAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.detail_list_row, parent, false);
        }

        TextView nameView = convertView.findViewById(R.id.textView_detail_name);
        TextView unitView = convertView.findViewById(R.id.textView_detail_unit);
        TextView quantityView = convertView.findViewById(R.id.textView_detail_quantity);

        nameView.setText("Name: " + item.getName());
        unitView.setText("Unit: " + item.getUnit());
        quantityView.setText("Quantity: " + item.getQuantity());

        return convertView;    }
}
