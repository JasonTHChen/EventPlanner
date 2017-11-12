package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Intent;
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

public class EventOptionDialog extends DialogFragment {
    private static final String TAG = EventOptionDialog.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static EventOptionDialog getInstance(long id) {
        EventOptionDialog dialog = new EventOptionDialog();
        Bundle args = new Bundle();
        args.putLong("eventId", id);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_options_dialog, container);
        getDialog().setTitle("Event Options");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView editView = view.findViewById(R.id.textView_options_edit);
        final TextView deleteView = view.findViewById(R.id.textView_options_delete);
        final EventMasterDao eventMaster = new EventMasterDao(getActivity());
        final long eventId = getArguments().getLong("eventId");

        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddEventActivity.class);
                i.putExtra("id", eventId);
                i.putExtra("mode", 1);
                dismiss();
                startActivityForResult(i, 1);
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventMaster.delete(eventId);
                eventMaster.close();
                getActivity().recreate();
                dismiss();
            }
        });
    }
}
