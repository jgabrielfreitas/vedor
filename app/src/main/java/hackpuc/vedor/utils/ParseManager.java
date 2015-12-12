package hackpuc.vedor.utils;

import android.content.Context;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ParseManager {

    public static final String POLITICS = "politics";

    public static ParseRequest createParserRequest(String classToRequest) {
        return new ParseRequest(classToRequest);
    }

    public static CustomParseRequest createCustomParserRequest(Context context) {
        return new CustomParseRequest(context, POLITICS);
    }
}
