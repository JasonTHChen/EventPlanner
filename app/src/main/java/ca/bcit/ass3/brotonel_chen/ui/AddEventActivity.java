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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.Event;

public class AddEventActivity extends AppCompatActivity {

    Button addButton, cancelButton;
    EditText dateEdit, timeEdit, nameEdit;
    Calendar calendar = Calendar.getInstance();
    int mode = 0;
    Event event;
    private EventMasterDao eventMaster;

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

        eventMaster = new EventMasterDao(this);
        mode = getIntent().getIntExtra("mode", 0);
        long id = getIntent().getLongExtra("id", 0);

        if (mode == 1) {
            addButton.setText("Edit");
            if (id > 0) {

                event = eventMaster.findEventById(id);

                nameEdit.setText(event.getName());
                dateEdit.setText(event.getDate());
                timeEdit.setText(event.getTime());
            }
        }
        //String name = getIntent().getStringExtra("name");
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
                timeEdit.setText(selectedHour + ":" + selectedMinute + " " + AM_PM);
            }
        }, hour, minute, true);

        timePicker.setTitle("Select Time");
        timePicker.show();
    }

    public void onAddClick(View v) {
        if (event == null) {
            event = new Event();
        }
        event.setName(nameEdit.getText().toString());
        event.setDate(dateEdit.getText().toString());
        event.setTime(timeEdit.getText().toString());

        if (event.getName().isEmpty()) {
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (event.getDate().isEmpty()) {
            Toast.makeText(this, "Date cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (event.getTime().isEmpty()) {
            Toast.makeText(this, "Time cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            if (mode == 1) {
                //System.out.println(event.getName());
                eventMaster.update(event);
            } else {
                eventMaster.insert(event);
            }
            Intent i = new Intent();
            this.setResult(Activity.RESULT_OK, i);
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventMaster.close();
        eventMaster = null;
    }

    public void onCancelClick(View v) {
        this.finish();
    }
}
