package sk.peterziska.funtasty;

import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import sk.peterziska.funtasty.Services.MeteorAPI;

public class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        // Do some work here
        Toast.makeText(getApplicationContext(),"text",Toast.LENGTH_SHORT).show();
        Log.e("TEST","DISPATCHER");
        new MeteorAPI();
        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // Answers the question: "Should this job be retried?"
    }
}