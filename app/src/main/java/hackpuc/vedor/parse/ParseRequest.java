package hackpuc.vedor.parse;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import hackpuc.vedor.interfaces.ParseCallback;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ParseRequest {

    protected ParseCallback parseCallback;
    protected ParseQuery<ParseObject> query;

    public ParseRequest(String classToRequest) {
        query = ParseQuery.getQuery(classToRequest);
        query.setLimit(1000);
        addWhereEqualsTo(ParseFields.POLITIC_NUM_TURNO, "1");
    }

    public ParseRequest addWhereEqualsTo(String key, String value){
        query.whereEqualTo(key, value);
        return this;
    }

    public ParseRequest addWhereDoesNotExist(String key) {
        query.whereDoesNotExist(key);
        return this;
    }

    public ParseRequest addWhereExists(String key){
        query.whereExists(key);
        return this;
    }

    public ParseRequest addWhereStartWith(String key, String value) {
        query.whereStartsWith(key, value);
        return this;
    }

    public void doRequest() {

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if(getParseCallback() != null) {

                    if (e == null)
                        getParseCallback().onSuccess(objects);
                    else
                        getParseCallback().onError(e);

                }
            }
        });
    }

    public ParseCallback getParseCallback() {
        return parseCallback;
    }

    public ParseRequest setParseCallback(ParseCallback parseCallback) {
        this.parseCallback = parseCallback;
        return this;
    }
}
