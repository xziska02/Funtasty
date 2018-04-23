package sk.peterziska.funtasty.Presenter;

import android.util.Log;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import jonathanfinerty.once.Once;
import sk.peterziska.funtasty.Data.DatabaseManager;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.Data.Services.MeteorAPI;
import sk.peterziska.funtasty.MyTaskService;
import sk.peterziska.funtasty.UI.Activity.MeteorActivity;

public class MainPresenter {

    private MeteorActivity mMeteorActivity;
    private static final String INIT_SYNC_ON_INSTALL = "INIT_FUNTASTY_APP";


    public MainPresenter(MeteorActivity activity){
        mMeteorActivity = activity;
        //getMeteors();

        RealmResults<Meteor> meteors = DatabaseManager.getInstance().getMeteors();
        meteors.addChangeListener(new RealmChangeListener<RealmResults<Meteor>>() {
            @Override
            public void onChange(RealmResults<Meteor> meteors) {
                Log.e("DATA","CHANGED");
                setRecyclerData();
            }
        });

        if (DatabaseManager.getInstance().isDatabaseEmpty()){
            new MeteorAPI();
        }else{
            setRecyclerData();
        }
        scheduleSync();
    }

    private void scheduleSync() {
        MyTaskService.scheduleSync(mMeteorActivity);
        /*Once.initialise(mMeteorActivity);
        if (!Once.beenDone(Once.THIS_APP_INSTALL, INIT_SYNC_ON_INSTALL)) {
            MyTaskService.scheduleSync(mMeteorActivity);
            Once.markDone(INIT_SYNC_ON_INSTALL);
        }else{
            Log.e("BEEN", "DONE");
            setRecyclerData();
        }*/
    }

    public void setRecyclerData(){
        mMeteorActivity.setMeteorRecyclerView(DatabaseManager.getInstance().getMeteors());
    }


    public List<Meteor> getMeteors(){
        MeteorAPI api = new MeteorAPI();
        return api.getMeteors();
    }
}
