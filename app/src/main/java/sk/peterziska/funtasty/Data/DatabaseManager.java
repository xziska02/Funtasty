package sk.peterziska.funtasty.Data;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


    /**
     * save to database
     * @param meteors
     */
    public void saveToDatabase(final List<Meteor> meteors) {
        realm.beginTransaction();
        realm.insertOrUpdate(meteors);
        realm.commitTransaction();
    }

    public boolean isDatabaseEmpty(){
        return realm.isEmpty();
    }

    /**
     * Returns meteors from 2011
     * @return
     */
    public RealmResults<Meteor> getMeteors(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 31); // make sure month stays valid
        calendar.set(Calendar.YEAR, 2010);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        Date date = new Date(calendar.getTimeInMillis());
        return realm.where(Meteor.class).
                greaterThanOrEqualTo("year", date).
                findAll().sort("mass", Sort.DESCENDING);
    }

    /**
     * Close realm and null instance object
     */
    public void realmClose(){
        instance = null;
        realm.close();
    }
}
