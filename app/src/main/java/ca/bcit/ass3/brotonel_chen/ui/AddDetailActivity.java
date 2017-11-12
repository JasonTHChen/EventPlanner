package ca.bcit.ass3.brotonel_chen.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventDetailDao;
import ca.bcit.ass3.brotonel_chen.model.Item;

public class AddDetailActivity extends AppCompatActivity {

    Item item;
    EditText nameEdit, unitEdit, quantityEdit;
    long eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);

        nameEdit = (EditText) findViewById(R.id.editText_addDetail_name);
        unitEdit = (EditText) findViewById(R.id.editText_addDetail_unit);
        quantityEdit = (EditText) findViewById(R.id.editText_addDetail_quantity);
        final Button addButton = (Button) findViewById(R.id.button_addDetail_add);
        final Button cancelButton = (Button) findViewById(R.id.button_addDetail_cancel);
        eventId = getIntent().getLongExtra("eventId", 0);
        long itemId = getIntent().getLongExtra("itemId", 0);
        final int mode = getIntent().getIntExtra("mode", 0);
        if (mode == 1) {
            addButton.setText("Edit");
            if (itemId > 0) {
                EventDetailDao eventDetail = new EventDetailDao(AddDetailActivity.this);
                eventDetail.open();
                Item item = eventDetail.findItemById(itemId);
                nameEdit.setText(item.getName());
                unitEdit.setText(item.getUnit());
                quantityEdit.setText(String.valueOf(item.getQuantity()));
                eventDetail.close();
            }
        }


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDetailDao eventDetail = new EventDetailDao(AddDetailActivity.this);
                if (item == null) {
                    item = new Item();
                }

                item.setName(nameEdit.getText().toString());
                item.setUnit(unitEdit.getText().toString());
                item.setQuantity(Integer.parseInt(quantityEdit.getText().toString()));

                if (item.getName().trim().isEmpty()) {
                    Toast.makeText(AddDetailActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (item.getUnit().trim().isEmpty()) {
                    Toast.makeText(AddDetailActivity.this, "Unit cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (item.getQuantity() == 0) {
                    Toast.makeText(AddDetailActivity.this, "Quantity cannot be 0", Toast.LENGTH_SHORT).show();
                } else {
                    eventDetail.open();
                    if (mode == 1) {
                        eventDetail.update(item);
                    } else {
                        eventDetail.insert(item, eventId);
                    }
                    eventDetail.close();
                    Intent i = new Intent();
                    AddDetailActivity.this.setResult(Activity.RESULT_OK, i);
                    AddDetailActivity.this.finish();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDetailActivity.this.finish();
            }
        });
    }
}
