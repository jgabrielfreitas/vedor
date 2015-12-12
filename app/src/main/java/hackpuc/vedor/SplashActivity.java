package hackpuc.vedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

import hackpuc.vedor.interfaces.ParseCallback;
import hackpuc.vedor.objects.Politic;
import hackpuc.vedor.utils.ParseFields;
import hackpuc.vedor.utils.ParseManager;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressBar loadingProgressBar;
    TextView    loadingTextView;
    Button      tryAgainButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialize();

        instanceViews();
    }

    private void instanceViews() {

        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);
        loadingTextView    = (TextView)    findViewById(R.id.loadingTextView);
        tryAgainButton     = (Button)      findViewById(R.id.tryAgainButton);

        tryAgainButton.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        doRequest();
    }

    private void initialize() {
        try {
            Parse.initialize(this);
        } catch (Exception e) {}
    }

    private void doRequest(){

        // request Parse datas
        ParseManager.createCustomParserRequest(this, ParseManager.POLITICS)
                    .addWhereStartWith(ParseFields.POLITIC_NAME, "AÉCIO")
                    .addWhereEqualsTo(ParseFields.POLITIC_DESC_TURN, "NÃO ELEITO")
                    .setParseCallback(new ParseCallback() {
                        public void onSuccess(List<ParseObject> parseObjects) {
                            Log.e("Response", "Total of rows: " + parseObjects.size());
                            for (ParseObject object : parseObjects) {
                                Politic politic = new Politic(object);
                                Log.e("Name", "Name: " + politic.getCandidateName());
                            }
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }

                        public void onError(ParseException e) {
                            e.printStackTrace();
                            tryAgainButton.setVisibility(View.VISIBLE);
                            loadingProgressBar.setVisibility(View.GONE);
                            loadingTextView.setText("Ocorreu um erro, tente novamente.");
                        }
                    })
                    .doRequest();
    }

    public void onClick(View v) {

        tryAgainButton.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.VISIBLE);
        loadingTextView.setText("Carregando...");

        doRequest();
    }
}
