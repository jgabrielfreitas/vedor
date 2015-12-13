package hackpuc.vedor.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseUser;

import hackpuc.vedor.R;
import hackpuc.vedor.interfaces.ParseLoginCallback;
import hackpuc.vedor.parse.ParseManager;
import hackpuc.vedor.utils.ApplicationCache;
import hackpuc.vedor.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    AutoCompleteTextView userAutoCompleteTextView;
    AutoCompleteTextView passwordAutoCompleteTextView;
    Button loginButton;
    TextView createAccountTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);

        instanceViews();
    }

    private void instanceViews() {

        userAutoCompleteTextView     = (AutoCompleteTextView) findViewById(R.id.userAutoCompleteTextView);
        passwordAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.passwordAutoCompleteTextView);
        createAccountTextView        = (TextView)             findViewById(R.id.createAccountTextView);
        loginButton                  = (Button)               findViewById(R.id.loginButton);

        createAccountTextView.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.createAccountTextView:
                // new activity to create account
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
                finish();
                break;

            case R.id.loginButton:

                // consume Parse to do login
                if (Utils.validateString(userAutoCompleteTextView.getText().toString()) == false   ||
                    Utils.validateString(passwordAutoCompleteTextView.getText().toString()) == false) {
                    Toast.makeText(LoginActivity.this, "Campos inválidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                // do request
                ParseManager.createLogin(userAutoCompleteTextView.getText().toString(),
                                         passwordAutoCompleteTextView.getText().toString()).
                        setParseLoginCallback(new ParseLoginCallback() {
                            public void onSucess(ParseUser user) {

                                try {
                                    // save user's info
                                    new ApplicationCache(LoginActivity.this).saveObjectEncrypted(Utils.FILE_USER, new Gson().toJson(user));

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onError(ParseException e) {
                                e.printStackTrace();
                            }
                        }).doRequest();


                break;
        }
    }
}
