package hackpuc.vedor.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.CandidateAdapter;
import hackpuc.vedor.objects.Politic;

public class CandidateActivity extends AppCompatActivity {

    private ListView listView;
    private String from;
    private CandidateAdapter candidateAdapter;
    private TextView withoutCandidatesToShowTextView;

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
        withoutCandidatesToShowTextView = (TextView) findViewById(R.id.withoutCandidatesToShowTextView);

        listView.setAdapter(candidateAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Politic politic = (Politic) listView.getAdapter().getItem(position);
                Intent intent = new Intent(CandidateActivity.this, DetailsActivity.class);
                intent.putExtra("name", politic.getCandidatePartyName());
                intent.putExtra("party", politic.getCandidatePartyInitials());
                intent.putExtra("number", politic.getCandidatePartyNumber());
                intent.putExtra("cpf", politic.getCandidateDocumentNumber());
                intent.putExtra("email", politic.getCandidateEmail());
                startActivity(intent);

            }
        });

        if(listView.getAdapter().getCount() <= 0)
            withoutCandidatesToShowTextView.setVisibility(View.VISIBLE);

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
