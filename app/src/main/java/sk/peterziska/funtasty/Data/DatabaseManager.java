package sk.peterziska.funtasty.Data;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseManager {

    private static DatabaseManager instance;
    private Realm realm;

    public DatabaseManager() {
        realm = Realm.getDefaultInstance();
    }

    public static DatabaseManager getInstance( ) {
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }



    public void saveToDatabase(final List<Meteor> info) {
        realm.beginTransaction();
        realm.insertOrUpdate(info);
        realm.commitTransaction();
        Log.e("DATABASE","SIZE" + getMeteors().size());
    }

    public boolean isDatabaseEmpty(){
        return realm.isEmpty();
    }

    public RealmResults<Meteor> getMeteors(){
        return realm.where(Meteor.class).findAll().sort("mass", Sort.DESCENDING);
    }
}
