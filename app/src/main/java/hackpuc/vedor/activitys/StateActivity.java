package hackpuc.vedor.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.OfficeAdapter;
import hackpuc.vedor.interfaces.ParseCallback;
import hackpuc.vedor.objects.Politic;
import hackpuc.vedor.utils.ParseFields;
import hackpuc.vedor.utils.ParseManager;

public class StateActivity extends AppCompatActivity {

    private ListView listView;
    private String statusCode;

    public static List<Politic> politicList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        // Insert return icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getSupportActionBar().setTitle(extras.getString("name"));
            statusCode= extras.getString("code");
        }

        List<String> offStringList = new ArrayList<>();
        offStringList.add("1º Suplente");
        offStringList.add("2º Suplente");

        if (extras.getString("code").equals("DF") == true)
            offStringList.add("Deputado Distrital");
        else
            offStringList.add("Deputado Estadual");

        offStringList.add("Deputado Federal");
        offStringList.add("Governador");
        offStringList.add("Vice-Governador");
        offStringList.add("Senador");

        OfficeAdapter officeAdapter = new OfficeAdapter(this, offStringList);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(officeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // create request here and sent
                ParseManager.createCustomParserRequest(StateActivity.this).setWorkInBackground(false)
                            .setDialogMessage("Carregando...")
                        .addWhereEqualsTo(ParseFields.POLITIC_UF, statusCode)
                        .addWhereEqualsTo(ParseFields.POLITIC_CARGO, ((OfficeAdapter) listView.getAdapter()).getItem(position).toUpperCase())
                .setParseCallback(new ParseCallback() {
                    public void onSuccess(List<ParseObject> parseObjects) {

                        politicList = new ArrayList<>();

                        for (ParseObject parseObject : parseObjects) {
                            Log.e("Response", "Name:" + new Politic(parseObject));
                            politicList.add(new Politic(parseObject));
                        }
//                        Log.e("Response", "Total of rows: " + parseObjects.size());
                        Intent intent = new Intent(StateActivity.this, CandidateActivity.class);
                        intent.putExtra("from", "state");
                        startActivity(intent);
                    }

                    public void onError(ParseException e) {

                    }
                }).doRequest();
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
