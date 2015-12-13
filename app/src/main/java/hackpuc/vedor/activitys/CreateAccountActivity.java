package hackpuc.vedor.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import hackpuc.vedor.R;
import hackpuc.vedor.utils.ApplicationCache;
import hackpuc.vedor.utils.Utils;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView userAutoCompleteTextView;
    AutoCompleteTextView emailAutoCompleteTextView;
    AutoCompleteTextView passwordAutoCompleteTextView;
    AutoCompleteTextView passwordRewriteAutoCompleteTextView;
    Button createAccountButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        instanceViews();
    }

    private void instanceViews() {

        userAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.userAutoCompleteTextView);
        passwordAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.passwordAutoCompleteTextView);
        emailAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.emailAutoCompleteTextView);
        createAccountButton = (Button) findViewById(R.id.createAccountButton);
        passwordRewriteAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.passwordRewriteAutoCompleteTextView);

        createAccountButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (!Utils.validateString(userAutoCompleteTextView.getText().toString())     ||
            !Utils.validateString(passwordAutoCompleteTextView.getText().toString()) ||
            !Utils.validateString(emailAutoCompleteTextView.getText().toString())    ||
            !Utils.validateString(passwordRewriteAutoCompleteTextView.getText().toString())) {

            Toast.makeText(CreateAccountActivity.this, "Campos inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (passwordAutoCompleteTextView.getText().toString().equals(passwordRewriteAutoCompleteTextView.getText().toString()) == false) {
            Toast.makeText(CreateAccountActivity.this, "Senhas diferentes", Toast.LENGTH_SHORT).show();
            return;
        }
        final ParseUser user = new ParseUser();
        user.setUsername(userAutoCompleteTextView.getText().toString());
        user.setPassword(passwordAutoCompleteTextView.getText().toString());
        user.setEmail(emailAutoCompleteTextView.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                try {
                    if (e == null) { // user registed
                        // save user's info
                        new ApplicationCache(CreateAccountActivity.this).saveObjectEncrypted(Utils.FILE_USER, new Gson().toJson(user));

                        Toast.makeText(CreateAccountActivity.this, "Bem vindo ao Vedor!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));
                        finish();
                    } else
                        Toast.makeText(CreateAccountActivity.this, "Erro na criação da conta. Tente novamente", Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
