package hackpuc.vedor.activitys;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.CandidateAdapter;

public class CandidateActivity extends AppCompatActivity {

    private ListView listView;
    private String from;
    private CandidateAdapter candidateAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        // Insert return icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");
        }

        if(from.equals("state") == true)
            candidateAdapter = new CandidateAdapter(CandidateActivity.this, StateActivity.politicList);
        else
            candidateAdapter = new CandidateAdapter(CandidateActivity.this, MainActivity.politicList);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(candidateAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    // Add arrow in action bar to back on activity
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
