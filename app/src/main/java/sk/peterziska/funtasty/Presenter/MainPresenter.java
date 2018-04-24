package sk.peterziska.funtasty.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.util.TimeUtils;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import java.time.DayOfWeek;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import sk.peterziska.funtasty.Data.DatabaseManager;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.MyJobService;
import sk.peterziska.funtasty.Services.MeteorAPI;
import sk.peterziska.funtasty.UI.Activity.MeteorActivity;



public class MainPresenter implements PresenterInterface{


    private final int DAY_NUMBER = 1;
    private final int HALF_HOUR = 30;
    private MeteorActivity mMeteorActivity;
    RealmResults<Meteor> meteors;
    private String TAG = MainPresenter.class.getCanonicalName();


    public MainPresenter(MeteorActivity activity){
        mMeteorActivity = activity;

        meteors = DatabaseManager.getInstance().getMeteors();
        meteors.addChangeListener(new RealmChangeListener<RealmResults<Meteor>>() {
            @Override
            public void onChange(RealmResults<Meteor> meteors) {        //change listener for realm database
                setRecyclerData(meteors);
            }
        });

        if (DatabaseManager.getInstance().isDatabaseEmpty()){
            if (!isConnectedWifi()){                      //app was first time started without wifi enable
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (!isConnectedWifi()) {
                                WifiManager wifiManager = (WifiManager)mMeteorActivity.
                                        getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                                wifiManager.setWifiEnabled(true);
                                Thread.sleep(1000);
                            }
                            new MeteorAPI();
                        } catch (Exception e) {
                        }
                    }
                };
                t.start();
            }else {
                new MeteorAPI();
            }
        }else{
            setRecyclerData(DatabaseManager.getInstance().getMeteors());
        }
        scheduleSync();
    }

    private boolean isConnectedWifi() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager  = (ConnectivityManager) mMeteorActivity.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfos = connectivityManager .getAllNetworkInfo();
        for (NetworkInfo netInfo : netInfos) {
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    haveConnectedWifi = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void scheduleSync() {
        int dayInSeconds = (int)TimeUnit.DAYS.toSeconds(DAY_NUMBER);
        int halfHour = (int)TimeUnit.MINUTES.toSeconds(HALF_HOUR);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(mMeteorActivity));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setLifetime(Lifetime.FOREVER)
                .setTag(TAG)
                .setReplaceCurrent(false)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(dayInSeconds-halfHour, dayInSeconds))
                .setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();

        dispatcher.mustSchedule(myJob);
    }

    public void setRecyclerData(List<Meteor> meteors){
        mMeteorActivity.setMeteorRecyclerView(meteors);
        mMeteorActivity.setSumMeteorsTextView(String.valueOf(meteors.size()));
        mMeteorActivity.hideProgressBar();
    }

    @Override
    public void unregister() {
        DatabaseManager.getInstance().realmClose();
    }
}
