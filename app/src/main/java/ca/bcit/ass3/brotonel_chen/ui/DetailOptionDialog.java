package ca.bcit.ass3.brotonel_chen.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventDetailDao;
import ca.bcit.ass3.brotonel_chen.model.Item;

/**
 * Created by Jason on 08-Nov-2017.
 */

public class DetailOptionDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    public static DetailOptionDialog getInstance(String title, long itemId) {
        DetailOptionDialog dialog = new DetailOptionDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putLong("itemId", itemId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_options_dialog, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView editView = view.findViewById(R.id.textView_options_edit);
        final TextView deleteView = view.findViewById(R.id.textView_options_delete);
        final long itemId = getArguments().getLong("itemId");

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailDao eventDetail = new EventDetailDao(getActivity());
                eventDetail.open();
                Item item = eventDetail.findItemById(itemId);
                eventDetail.close();
                Intent i = new Intent(getActivity(), AddDetailActivity.class);
                i.putExtra("itemId", item.getItemId());
                i.putExtra("mode", 1);
                dismiss();
                getActivity().startActivityForResult(i, 1);
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailDao eventDetail = new EventDetailDao(getActivity());
                eventDetail.open();
                eventDetail.delete(itemId);
                eventDetail.close();
                getActivity().recreate();
                dismiss();
            }
        });

        String title = getArguments().getString("title");
        getDialog().setTitle(title);
    }
}
