package sk.peterziska.funtasty.UI.Activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.Presenter.MainPresenter;
import sk.peterziska.funtasty.R;
import sk.peterziska.funtasty.UI.Adapters.MeteorAdapter;

public class MeteorActivity extends AppCompatActivity {

    @BindView(R.id.meteor_recycler)
    RecyclerView mMeteorRecyclerView;

    @BindView(R.id.sum_meteors_text)
    TextView mSumMeteorsTextView;

    @BindView(R.id.progress_bar_layout)
    ConstraintLayout mConstraintLayout;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);                  //Binder

        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter = new MainPresenter(this);   //Set presenter

    }

    //Hide progressbarr, when data is available
    public void hideProgressBar(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mConstraintLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregister();
    }

    public void setSumMeteorsTextView(String sum){
        mSumMeteorsTextView.setText(sum);
    }

    public void setMeteorRecyclerView(List<Meteor> data){
        mMeteorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MeteorAdapter adapter = new MeteorAdapter(this,data);
        mMeteorRecyclerView.setAdapter(adapter);
    }
}
