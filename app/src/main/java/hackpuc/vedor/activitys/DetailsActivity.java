package hackpuc.vedor.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hackpuc.vedor.R;
import hackpuc.vedor.utils.Utils;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView nameCandidateTextView;
    private ImageView partyImageView;
    private TextView partyTextView;
    private TextView documentTextView;
    private AutoCompleteTextView emailAutoCompleteTextView;
    private Button sendEmailButton;
    private String email;
    private String candidateId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameCandidateTextView     = (TextView)  findViewById(R.id.nameCandidateTextView);
        partyImageView            = (ImageView) findViewById(R.id.partyImageView);
        partyTextView             = (TextView)  findViewById(R.id.partyTextView);
        documentTextView          = (TextView)  findViewById(R.id.documentTextView);
        sendEmailButton           = (Button)    findViewById(R.id.sendEmailButton);
        emailAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.emailAutoCompleteTextView);

        sendEmailButton.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    protected void onResume() {
        super.onResume();

        // Insert return icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameCandidateTextView.setText(extras.getString("name"));
            partyImageView.setImageResource(Utils.inputBrandParty(extras.getString("party")));
            partyTextView.setText(extras.getString("number") + " - " + extras.getString("party"));
            documentTextView.setText(Utils.formatCpf(extras.getString("cpf")));
            candidateId = extras.getString("candidateId");
            if (extras.getString("email").equals("NULL") == true) {
                emailAutoCompleteTextView.setEnabled(false);
                sendEmailButton.setEnabled(false);
            } else {
                email = extras.getString("email");
            }
        }
    }

    public void onClick(View v) {

        if (emailAutoCompleteTextView.getText().toString().trim().length() == 0){
            emailAutoCompleteTextView.setError("Digite uma mensagem");
            return;
        }

        Utils.sendEmail(email, emailAutoCompleteTextView.getText().toString());
    }

    // Add arrow in action bar to back on activity
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;
            case R.id.create_post:
                Intent intent = new Intent(this, CreatePostActivity.class);
                intent.putExtra("candidateId", candidateId);
                startActivity(intent);
                break;
            case R.id.read_feed:
                Intent intent2 = new Intent(this, FeedActivity.class);
                intent2.putExtra("candidateId", candidateId);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
