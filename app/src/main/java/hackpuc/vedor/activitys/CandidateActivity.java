package hackpuc.vedor.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.CandidateAdapter;
import hackpuc.vedor.adapter.CandidateAdapter2;
import hackpuc.vedor.adapter.OfficeAdapter;
import hackpuc.vedor.objects.Politic;


public class CandidateActivity extends AppCompatActivity {

    private ListView listView;
    private String from;
    private CandidateAdapter2 candidateAdapter2;
    private TextView withoutCandidatesToShowTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);
    }

    protected void onResume() {
        super.onResume();

        // Insert return icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getString("from");
        }

        if(from.equals("state") == true)
            candidateAdapter2 = new CandidateAdapter2(CandidateActivity.this, StateActivity.politicList);
        else
            candidateAdapter2 = new CandidateAdapter2(CandidateActivity.this, MainActivity.politicList);


        withoutCandidatesToShowTextView = (TextView) findViewById(R.id.withoutCandidatesToShowTextView);

        listView.setAdapter(candidateAdapter2);

        if(listView.getAdapter().getCount() <= 0)
            withoutCandidatesToShowTextView.setVisibility(View.VISIBLE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Politic politic = (Politic) listView.getAdapter().getItem(position);
                Intent intent = new Intent(CandidateActivity.this, DetailsActivity.class);
                intent.putExtra("name", politic.getCandidateName());
                intent.putExtra("party", politic.getCandidatePartyInitials());
                intent.putExtra("number", politic.getCandidatePartyNumber());
                intent.putExtra("candidateId", politic.getObjectId());
                intent.putExtra("cpf", politic.getCandidateDocumentNumber().replaceAll("[\\D]", ""));
                intent.putExtra("email", politic.getCandidateEmail());
                startActivity(intent);
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
