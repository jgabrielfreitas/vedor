package hackpuc.vedor.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import hackpuc.vedor.R;

public class FeedActivity extends AppCompatActivity {

    ListView feedListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        instanceViews();
    }

    private void instanceViews() {

        feedListView = (ListView) findViewById(R.id.feedListView);
    }
}
