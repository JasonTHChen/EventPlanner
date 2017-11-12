package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ca.bcit.ass3.brotonel_chen.R;

public class MainActivity extends AppCompatActivity implements EventMasterFragment.OnEventSelectListener, EventDetailFragment.ItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_main_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
        }

        EventMasterFragment eventFragment = new EventMasterFragment();
        eventFragment.setArguments(getIntent().getExtras());
        this.getSupportFragmentManager().beginTransaction().add(R.id.fragment_main_container, eventFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.finish();
                this.startActivity(getIntent());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_event_action:
                Intent i = new Intent(MainActivity.this, AddEventActivity.class);
                i.putExtra("mode", 0);
                startActivityForResult(i, 1);
                return true;
            case R.id.search_event_action:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onEventSelect(long id) {
        // TODO: for tablet
        //EventDetailFragment itemFragment = (EventDetailFragment) getSupportFragmentManager().findFragmentById(R.id)

        FragmentManager fm = getSupportFragmentManager();
        EventOptionDialog dialog = EventOptionDialog.getInstance("Option Menu", id);
        dialog.show(fm, "event_options_dialog");
    }

    @Override
    public void onItemSelect(long id) {
        FragmentManager fm = getSupportFragmentManager();
        DetailOptionDialog dialog = DetailOptionDialog.getInstance("Option Menu", id);
        dialog.show(fm, "detail_options_dialog");
    }
}
