package hackpuc.vedor.parse;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import hackpuc.vedor.interfaces.ParseLoginCallback;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ParseLoginRequest {

    String user;
    String password;
    ParseLoginCallback parseLoginCallback;

    public ParseLoginRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public ParseLoginCallback getParseLoginCallback() {
        return parseLoginCallback;
    }

    public ParseLoginRequest setParseLoginCallback(ParseLoginCallback parseLoginCallback) {
        this.parseLoginCallback = parseLoginCallback;
        return this;
    }

    public void doRequest() {

        ParseUser.logInInBackground(user, password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (getParseLoginCallback() != null) {
                    if (e == null)
                        getParseLoginCallback().onSucess(user);
                    else
                        getParseLoginCallback().onError(e);
                }

            }
        });
    }
}
