package sk.peterziska.funtasty.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Geolocation extends RealmObject {


    @SerializedName("coordinates")
    private RealmList<String> coordinates;

    public Geolocation() {
    }

    public RealmList<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(RealmList<String> coordinates) {
        this.coordinates = coordinates;
    }
}
