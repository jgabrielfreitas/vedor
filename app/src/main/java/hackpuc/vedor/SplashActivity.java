package hackpuc.vedor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import hackpuc.vedor.interfaces.ParseCallback;
import hackpuc.vedor.objects.Politic;
import hackpuc.vedor.utils.ParseFields;
import hackpuc.vedor.utils.ParseManager;

public class SplashActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialize();

        // Waiting 2 seconds before start main project
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }

    protected void onResume() {
        super.onResume();
        // request Parse datas

        ParseManager.parseCreator("politics").addWhereStartWith(ParseFields.POLITIC_NAME, "JAIR")
                                             .addWhereEqualsTo(ParseFields.POLITIC_NUM_PART, "11")
                                             .addWhereEqualsTo(ParseFields.POLITIC_UF, "RJ")
                                             .setParseCallback(new ParseCallback() {
                                                 public void onSuccess(List<ParseObject> parseObjects) {
                                                     Log.e("Response", "Total of rows: " + parseObjects.size());
                                                     for (ParseObject object : parseObjects) {
                                                         Politic politic = new Politic(object);
                                                         Log.e("Name", "Name: " + politic.getCandidateName());
                                                     }
                                                 }
                                                 public void onError(ParseException e) {
                                                     e.printStackTrace();
                                                 }
                                             })
                                             .doRequest();

        // facebook
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("politics");
//        query.setLimit(1000);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> objects, ParseException e) {
//
//                if (objects != null) {
//                    Log.e("Response", "Total of rows: " + objects.size());
//                    for (ParseObject object : objects) {
//                        Politic politic = new Politic(object);
//                        Log.e("Name", "Name: " + politic.getCandidateName() + "| Party: " + politic.getCandidatePartyName());
//                    }
//                } else
//                    e.printStackTrace();
//            }
//        });

    }

    private void initialize() {
        try{
            Parse.initialize(this);
        }catch (Exception e) {}
    }

}
