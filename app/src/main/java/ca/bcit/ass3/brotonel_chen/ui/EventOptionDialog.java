package ca.bcit.ass3.brotonel_chen.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

/**
 * Created by Jason on 08-Nov-2017.
 */

public class EventOptionDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);
    }

    public static EventOptionDialog getInstance(String title, long eventId) {
        EventOptionDialog dialog = new EventOptionDialog();
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
                EventMasterDao eventMasterDao = new EventMasterDao(getActivity());
                eventMasterDao.open();
                PartyEvent partyEvent = eventMasterDao.findPartyEventById(eventId);
                eventMasterDao.close();
                Intent i = new Intent(getActivity(), AddEventActivity.class);
                i.putExtra("id", partyEvent.getEventId());
                i.putExtra("mode", 1);
                dismiss();
                startActivityForResult(i, 1);
            }
        });

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventMasterDao eventMasterDao = new EventMasterDao(getActivity());
                eventMasterDao.open();
                eventMasterDao.delete(eventId);
                eventMasterDao.close();
                getActivity().recreate();
                dismiss();
            }
        });

        viewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailFragment itemFragment = new EventDetailFragment();
                Bundle args = new Bundle();
                args.putLong("eventId", eventId);
                itemFragment.setArguments(args);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_main_container, itemFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                dismiss();
            }
        });


        String title = getArguments().getString("title");
        this.getDialog().setTitle(title);
    }
}
