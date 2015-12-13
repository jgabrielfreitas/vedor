package hackpuc.vedor.interfaces;

import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public interface ParseLoginCallback {
    
    void onSucess(ParseUser user);

    void onError(ParseException e);
}
