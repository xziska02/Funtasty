package sk.peterziska.funtasty;

import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import sk.peterziska.funtasty.Services.MeteorAPI;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters job) {

        MeteorAPI.getInstance().fetchData();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}