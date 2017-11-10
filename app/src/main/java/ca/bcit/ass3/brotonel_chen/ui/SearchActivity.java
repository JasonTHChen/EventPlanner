package ca.bcit.ass3.brotonel_chen.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import ca.bcit.ass3.brotonel_chen.R;
import ca.bcit.ass3.brotonel_chen.dao.EventMasterDao;
import ca.bcit.ass3.brotonel_chen.model.PartyEvent;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SearchView mSearchView;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchView = (SearchView) findViewById(R.id.searchView_search);
        mListView = (ListView) findViewById(R.id.listView_search);

        setupSearchView();

    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        EventMasterDao eventMasterDao = new EventMasterDao(this);
        eventMasterDao.open();
        ArrayList<PartyEvent> partyEvents = eventMasterDao.findPartyEventsByName(query);
        eventMasterDao.close();
        if (partyEvents != null) {
            EventMasterAdapter adapter = new EventMasterAdapter(this, partyEvents);
            mListView.setAdapter(adapter);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
