package hackpuc.vedor.interfaces;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public interface ParseCallback {

    void onSuccess(List<ParseObject> parseObjects);

    void onError(ParseException e);

}
