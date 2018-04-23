package sk.peterziska.funtasty.UI.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jonathanfinerty.once.Once;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.Presenter.MainPresenter;
import sk.peterziska.funtasty.MyTaskService;
import sk.peterziska.funtasty.R;
import sk.peterziska.funtasty.UI.Adapters.MeteorAdapter;

public class MeteorActivity extends AppCompatActivity {

    private static final String TAG = MeteorActivity.class.getCanonicalName();

    @BindView(R.id.meteor_recycler)
    RecyclerView mMeteorRecyclerView;

    private MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainPresenter(this);

        //setMeteorRecyclerView();
    }




    public void setMeteorRecyclerView(List<Meteor> data){
        mMeteorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Log.d("ADAPTER", "if" + mPresenter.getMeteors().get(1).getId());
        List<Meteor> meteorList = new ArrayList();
        Meteor m = new Meteor();
        m.setId("1");
        meteorList.add(m);
        MeteorAdapter adapter = new MeteorAdapter(data);
        mMeteorRecyclerView.setAdapter(adapter);
    }
}
