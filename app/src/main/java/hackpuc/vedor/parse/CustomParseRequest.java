package hackpuc.vedor.parse;

import android.app.ProgressDialog;
import android.content.Context;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class CustomParseRequest extends ParseRequest{

    Context context;
    protected boolean workInBackground = true;
    protected ProgressDialog dialog;
    private String dialogMessage;
    private String dialogTitle;

    public CustomParseRequest(Context context, String classToRequest) {
        super(classToRequest);
        this.context = context;
    }

    public void doRequest() {

        if (isWorkInBackground() == false && context != null)
            dialog = ProgressDialog.show(context, getDialogTitle(), getDialogMessage(), false, false);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (getParseCallback() != null) {

                    if (e == null)
                        getParseCallback().onSuccess(objects);
                    else
                        getParseCallback().onError(e);

                }

                tryCloseDialog();
            }
        });
    }

    public boolean isWorkInBackground() {
        return workInBackground;
    }

    public CustomParseRequest setWorkInBackground(boolean workInBackground) {
        this.workInBackground = workInBackground;
        return this;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public CustomParseRequest setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
        return this;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    public CustomParseRequest setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
        return this;
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
