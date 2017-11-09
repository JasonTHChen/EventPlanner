package ca.bcit.ass3.brotonel_chen.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

public class AddEventActivity extends AppCompatActivity {

    Button addButton, cancelButton;
    EditText dateEdit, timeEdit, nameEdit;
    Calendar calendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        addButton = (Button) findViewById(R.id.button_addEvent_add);
        cancelButton = (Button) findViewById(R.id.button_addEvent_cancel);
        nameEdit = (EditText) findViewById(R.id.editText_addEvent_name);
        dateEdit = (EditText) findViewById(R.id.editText_addEvent_date);
        timeEdit = (EditText) findViewById(R.id.editText_addEvent_time);
    }

    private void updateLabel() {
        String formatDate = "MMMM dd, yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate, Locale.CANADA);
        dateEdit.setText(sdf.format(calendar.getTime()));
    }

    public void onDateEditClick(View v) {
        new DatePickerDialog(AddEventActivity.this, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeEditClick(View v) {
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String AM_PM = "AM";
                if (selectedHour >= 12) {
                    AM_PM = "PM";
                }
                timeEdit.setText( selectedHour + ":" + selectedMinute + " " + AM_PM);
            }
        }, hour, minute, true);

        timePicker.setTitle("Select Time");
        timePicker.show();
    }

    public void onAddClick(View v) {
        EventMasterDao eventMaster = new EventMasterDao(AddEventActivity.this);
        PartyEvent partyEvent = new PartyEvent();
        partyEvent.setName(nameEdit.getText().toString());
        partyEvent.setDate(dateEdit.getText().toString());
        partyEvent.setTime(timeEdit.getText().toString());
        eventMaster.open();
        eventMaster.insert(partyEvent);
        eventMaster.close();
        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void onCancelClick(View v) {
        this.finish();
    }
}
