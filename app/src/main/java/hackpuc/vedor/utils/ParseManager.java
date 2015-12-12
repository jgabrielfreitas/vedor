package hackpuc.vedor.utils;

import android.content.Context;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ParseManager {

    public static ParseRequest createParserRequest(String classToRequest) {
        return new ParseRequest(classToRequest);
    }

    public static CustomParseRequest createCustomParserRequest(Context context, String classToRequest) {
        return new CustomParseRequest(context, classToRequest);
    }
}
