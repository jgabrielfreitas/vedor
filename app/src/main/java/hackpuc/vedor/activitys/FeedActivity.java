package hackpuc.vedor.activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.FeedAdapter;
import hackpuc.vedor.interfaces.ParseCallback;
import hackpuc.vedor.objects.FeedItem;
import hackpuc.vedor.parse.ParseManager;

public class FeedActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView feedListView;
    String candidateId;
    TextView noFeedTextView;
    FeedAdapter feedAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        candidateId = getIntent().getExtras().getString("candidateId");

        instanceViews();
    }

    private void instanceViews() {

        noFeedTextView = (TextView) findViewById(R.id.noFeedTextView);
        feedListView   = (ListView) findViewById(R.id.feedListView);

        ParseManager.createParserRequest("Post")
                .setParseCallback(new ParseCallback() {
                    public void onSuccess(List<ParseObject> parseObjects) {
                        if (parseObjects.size() <= 0)
                            noFeedTextView.setVisibility(View.VISIBLE);
                        else {

                            List<FeedItem> feedItems = new ArrayList<FeedItem>();
                            for (ParseObject parseObject : parseObjects)
                                feedItems.add(new FeedItem(parseObject));

                            feedAdapter = new FeedAdapter(FeedActivity.this, feedItems);
                            feedListView.setAdapter(feedAdapter);
                        }
                    }

                    public void onError(ParseException e) {
                        e.printStackTrace();
                        noFeedTextView.setVisibility(View.VISIBLE);
                    }
                }).addWhereEqualsTo("candidateId", candidateId).doRequest();

        feedListView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FeedItem item = ((FeedAdapter) feedListView.getAdapter()).getItem(position);

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(item.getLink()));
        startActivity(i);

    }
}
