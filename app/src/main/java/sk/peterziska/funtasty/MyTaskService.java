package sk.peterziska.funtasty;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.TaskParams;

import sk.peterziska.funtasty.Data.Services.MeteorAPI;

public class MyTaskService extends GcmTaskService  {

    public static String TAG = "MYTASK_SCHEDULE1235648978";

    @Override
    public int onRunTask(TaskParams taskParams) {

        Log.e(TAG,"Data");
        MeteorAPI api = new MeteorAPI();
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i1) {
        Log.e(TAG,"START0");
        return super.onStartCommand(intent, i, i1);

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"BIND");
        return super.onBind(intent);
    }

    @Override
    public void onInitializeTasks() {
        super.onInitializeTasks();
        Log.e(TAG,"INIT");
    }

    public static void scheduleSync(Context ctx) {
        GcmNetworkManager gcmNetworkManager = GcmNetworkManager.getInstance(ctx);
        PeriodicTask periodicTask = new PeriodicTask.Builder()
                .setPeriod(10)
                .setRequiredNetwork(PeriodicTask.NETWORK_STATE_CONNECTED) //connection required
                .setTag(TAG)
                .setService(MyTaskService.class)
                .setUpdateCurrent(false)
                .setPersisted(true)
                .build();
        gcmNetworkManager.schedule(periodicTask);

    }


  /*  private static final String TAG = MyTaskService.class.getSimpleName();
    private List<Meteor> mMeteorList;
    JobParameters params;
    Realm realm = null;
    private DoItTask mDoItTask;

    public static final String GCM_REPEAT_TAG = "FUNTASTY.REPEAT.TAG";

    @Override
    public int onRunTask(TaskParams taskParams) {
        Log.e("JOB","DONE");
        Toast.makeText(getApplicationContext(),"TEST",Toast.LENGTH_LONG).show();
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    @Override
    public void onCreate()
    {
        Log.i(TAG, "in onCreate");
        super.onCreate();
    }

    @Override
    public void onInitializeTasks() {
        //called when app is updated to a new version, reinstalled etc.
        //you have to schedule your repeating tasks again
         Log.e("JOB","INIT");
        super.onInitializeTasks();
    }

    public static void scheduleRepeat(Context context, Bundle bundle) {
        //in this method, single Repeating task is scheduled (the target service that will be called is MyTaskService.class)
        try {
            PeriodicTask periodic = new PeriodicTask.Builder()
                    //specify target service - must extend GcmTaskService
                    .setService(MyTaskService.class)
                    //repeat every 60 seconds
                    .setPeriod(5)
                    //specify how much earlier the task can be executed (in seconds)
                    .setExtras(bundle)
                    //tag that is unique to this task (can be used to cancel task)
                    .setTag(GCM_REPEAT_TAG)
                    //whether the task persists after device reboot
                    .setPersisted(true)
                    //if another task with same tag is already scheduled, replace it with this task
                    .setUpdateCurrent(false)
                    //set required network state, this line is optional
                    .setRequiredNetwork(Task.NETWORK_STATE_ANY)
                    .build();
            GcmNetworkManager.getInstance(context).schedule(periodic);
            Log.v(TAG, "repeating task scheduled");
        } catch (Exception e) {
            Log.e(TAG, "scheduling failed");
            e.printStackTrace();
        }
    }


    private void loadData(){

        MeteorAPI api = new MeteorAPI();
        mMeteorList = api.getMeteors();
        if (mMeteorList == null){
            Log.d("TEST", "NULL");
        }

    }
    private class DoItTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {

            Realm realm = Realm.getDefaultInstance();
            Log.d("DoItTask", "Clean up the task here and call jobFinished...");



            try {
                // Work with
                Log.e("DoItTask", "Saving");

                DatabaseManager.getInstance().saveToDatabase(mMeteorList);
                //ControllerDB.getInstance().saveToDatabase(weather);
                Log.e("DoItTask", "Saved");
                //jobFinished(params,false);
            } finally {
                realm.close();
            }
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("DoItTask", "Working here...");
            loadData();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            loadData();
        }
    }
       /* private DoItTask mDoItTask;
        JobParameters params;
        private List<Meteor> mMeteorList;
        Realm realm = null;
        @Override
        public boolean onStartJob(final JobParameters params) {
            this.params = params;
            realm = null;
            mDoItTask = new DoItTask();
            mDoItTask.execute();
            return true;
        }

        private void loadData(){

            MeteorAPI api = new MeteorAPI();
            mMeteorList = api.getMeteors();
            if (mMeteorList == null){
                Log.d("TEST", "NULL");
            }

        }
        @Override
        public boolean onStopJob(JobParameters params) {
            if (mDoItTask != null)
                mDoItTask.cancel(true);
            return false;
        }

        private class DoItTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected void onPostExecute(Void aVoid) {

                Realm realm = Realm.getDefaultInstance();
                Log.d("DoItTask", "Clean up the task here and call jobFinished...");

                //ControllerDB.getInstance().saveToDatabase(realm,weather);

                try {
                    // Work with
                    Log.e("DoItTask", "Saving");
                    //ControllerDB.getInstance().saveToDatabase(weather);
                    Log.e("DoItTask", "Saved");
                    //jobFinished(params,false);
                } finally {
                    realm.close();
                }
                super.onPostExecute(aVoid);
            }

            @Override
            protected Void doInBackground(Void... params) {
                Log.e("DoItTask", "Working here...");
                loadData();
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
                loadData();
            }
        }*/

}
