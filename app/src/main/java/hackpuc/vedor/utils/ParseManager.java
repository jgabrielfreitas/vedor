package hackpuc.vedor.utils;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ParseManager {

    public static ParseRequest parseCreator(String classToRequest) {
        return new ParseRequest(classToRequest);
    }
}
