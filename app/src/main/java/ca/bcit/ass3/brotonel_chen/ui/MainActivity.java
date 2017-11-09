package ca.bcit.ass3.brotonel_chen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ca.bcit.ass3.brotonel_chen.R;

public class MainActivity extends AppCompatActivity implements EventMasterFragment.OnEventSelectListener {

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
                startActivity(getIntent());
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main_menu, menu);
        //MenuItem menuItem = menu.findItem(R.id.add_event_action);
        /*
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search_event_action);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        */
        //searchView.setOnQueryTextListener(MainActivity.this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_event_action:

                Intent i = new Intent(MainActivity.this, AddEventActivity.class);
                startActivityForResult(i, 1);

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = this.getLayoutInflater().inflate(R.layout.search_event_dialog, null);

                builder.setView(view);
                final AlertDialog dialog = builder.create();

                dialog.show();
*/
                return true;
            case R.id.search_event_action:
                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = this.getLayoutInflater().inflate(R.layout.search_event_dialog, null);

                builder.setView(view);
                final AlertDialog dialog = builder.create();

                dialog.show();
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onEventSelect(long id) {
        // TODO: for tablet
        //EventDetailFragment itemFragment = (EventDetailFragment) getSupportFragmentManager().findFragmentById(R.id)

        FragmentManager fm = getSupportFragmentManager();
        OptionDialog dialog = OptionDialog.getInstance("Option Menu", id);
        dialog.show(fm, "event_options_dialog");


/*
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view = this.getLayoutInflater().inflate(R.layout.event_options_dialog, null);
        final TextView editView = (TextView) findViewById(R.id.textView_options_edit);
        final TextView deleteView = (TextView) findViewById(R.id.textView_options_delete);
        final TextView viewView = (TextView) findViewById(R.id.textView_options_view);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        dialog.show();
        */
/*
        EventDetailFragment itemFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putLong("eventId", id);
        itemFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_main_container, itemFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        */
    }
}
