package hackpuc.vedor.activitys;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hackpuc.vedor.R;
import hackpuc.vedor.utils.Mask;
import hackpuc.vedor.utils.Utils;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView nameCandidateTextView;
    private ImageView partyImageView;
    private TextView partyTextView;
    private TextView documentTextView;
    private AutoCompleteTextView emailAutoCompleteTextView;
    private Button sendEmailButton;

    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nameCandidateTextView     = (TextView) findViewById(R.id.nameCandidateTextView);
        partyImageView            = (ImageView) findViewById(R.id.partyImageView);
        partyTextView             = (TextView) findViewById(R.id.partyTextView);
        documentTextView          = (TextView) findViewById(R.id.documentTextView);
        emailAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.emailAutoCompleteTextView);
        sendEmailButton           = (Button) findViewById(R.id.sendEmailButton);

        sendEmailButton.setOnClickListener(this);

        // Insert return icon in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameCandidateTextView.setText(extras.getString("name"));
            partyImageView.setImageResource(Utils.inputBrandParty(extras.getString("party")));
            partyTextView.setText(extras.getString("number") + " - " + extras.getString("party"));
            documentTextView.setText(Utils.formatCpf(extras.getString("cpf")));
            if (extras.getString("email").equals("NULL") == true) {
                emailAutoCompleteTextView.setEnabled(false);
                sendEmailButton.setEnabled(false);
            } else {
                email = extras.getString("email");
            }
        }
    }

    @Override
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
        }
        return super.onOptionsItemSelected(item);
    }
}
