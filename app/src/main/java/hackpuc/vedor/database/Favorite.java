package hackpuc.vedor.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;

import hackpuc.vedor.objects.Politic;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
@Table(name = "Favorites")
public class Favorite extends Model {

    @Column(name = "ObjectID")
    private String objectId;

    @Column(name = "Politic")
    private String politic;

    public Politic getPolitic() {
        return new Gson().fromJson(politic, Politic.class);
    }

    public void setPolitic(Politic politic) {
        this.politic = new Gson().toJson(politic);
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
