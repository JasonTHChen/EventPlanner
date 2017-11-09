package ca.bcit.ass3.brotonel_chen.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;

/**
 * Created by Jason on 08-Nov-2017.
 */

public class OptionDialog extends DialogFragment {

    //private MainActivity mActiviy;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    public static OptionDialog getInstance(String title, long eventId) {
        OptionDialog dialog = new OptionDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putLong("eventId", eventId);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_options_dialog, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView editView = view.findViewById(R.id.textView_options_edit);
        final TextView deleteView = view.findViewById(R.id.textView_options_delete);
        final TextView viewView = view.findViewById(R.id.textView_options_view);
        final long eventId = getArguments().getLong("eventId");
        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("EDIT");
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventMasterDao eventMasterDao = new EventMasterDao(getActivity());
                eventMasterDao.open();
                System.out.println(eventId);
                eventMasterDao.delete(eventId);
                eventMasterDao.close();
                getActivity().recreate();
                dismiss();
            }
        });
        String title = getArguments().getString("title");
        getDialog().setTitle(title);
    }
}
