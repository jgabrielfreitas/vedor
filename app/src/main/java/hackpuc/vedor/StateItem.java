package hackpuc.vedor;

/**
 * Created by Cristian on 11/12/2015.
 */
public class StateItem {

    private int imageState;
    private String nameState;
    private String codeState;

    public StateItem(int imageState, String nameState, String codeState) {
        this.imageState = imageState;
        this.nameState  = nameState;
        this.codeState  = codeState;
    }

    public int getImageState() {
        return imageState;
    }

    public void setImageState(int imageState) {
        this.imageState = imageState;
    }

    public String getNameState() {
        return nameState;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }

    public String getCodeState() {
        return codeState;
    }

    public void setCodeState(String codeState) {
        this.codeState = codeState;
    }
}
