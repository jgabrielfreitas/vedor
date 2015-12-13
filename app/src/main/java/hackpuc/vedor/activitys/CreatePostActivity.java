package hackpuc.vedor.activitys;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONObject;

import hackpuc.vedor.R;
import hackpuc.vedor.utils.ApplicationCache;
import hackpuc.vedor.utils.Utils;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener{

    AutoCompleteTextView titleAutoCompleteTextView;
    AutoCompleteTextView linkAutoCompleteTextView;
    Button sendPostButton;
    String candidateId;
    ProgressDialog dialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        candidateId = getIntent().getExtras().getString("candidateId");

        instanceViews();
    }

    private void instanceViews() {

        titleAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.titleAutoCompleteTextView);
        linkAutoCompleteTextView  = (AutoCompleteTextView) findViewById(R.id.linkAutoCompleteTextView);
        sendPostButton            = (Button)               findViewById(R.id.sendPostButton);

        sendPostButton.setOnClickListener(this);
    }

    public void onClick(View v) {

        if(!Utils.validateString(titleAutoCompleteTextView.getText().toString()) ||
           !Utils.validateString(linkAutoCompleteTextView.getText().toString())) {
            Toast.makeText(CreatePostActivity.this, "Campos inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            dialog = ProgressDialog.show(this, null, "Enviando post..", false, false);

            String json = new ApplicationCache(this).decryptFile(Utils.FILE_USER);

            JSONObject jsonObject = new JSONObject(json);

            String key = jsonObject.getString("state");
            String userId = new JSONObject(key).getString("objectId");

            ParseObject parseObject = new ParseObject("Post");
            parseObject.put("title", titleAutoCompleteTextView.getText().toString());
            parseObject.put("linkUrl", linkAutoCompleteTextView.getText().toString());
            parseObject.put("userId", userId);
            parseObject.put("candidateId", candidateId);
            parseObject.saveInBackground(new SaveCallback() {
                public void done(ParseException e) {
                    if(e == null) {
                        Toast.makeText(CreatePostActivity.this, "Post enviado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(CreatePostActivity.this, "Ocorreu um erro no envio do post", Toast.LENGTH_SHORT).show();

                    tryCloseDialog();
                }
            });
        } catch (Exception e) {
            tryCloseDialog();
            e.printStackTrace();
        }
    }

    private void tryCloseDialog() {

        try {

            if (dialog != null)
                if (dialog.isShowing())
                    dialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
