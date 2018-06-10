package sk.peterziska.meteors.UI.Activity;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sk.peterziska.meteors.Data.Meteor;
import sk.peterziska.meteors.Presenter.MainPresenter;
import sk.peterziska.meteors.R;
import sk.peterziska.meteors.UI.Adapters.MeteorAdapter;

public class MeteorActivity extends AppCompatActivity implements MeteorActivityInt{

    @BindView(R.id.meteor_recycler)
    RecyclerView mMeteorRecyclerView;
    @BindView(R.id.sum_meteors_text)
    TextView mSumMeteorsTextView;
    @BindView(R.id.progress_bar_layout)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.no_internet_image)
    ImageView mNoInternetImage;
    private MainPresenter mPresenter;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);                  //Binder
        mProgressBar.setVisibility(View.VISIBLE);
        mPresenter = new MainPresenter(this);   //Set presenter
    }

    public void showProgressBar(){

        mConstraintLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public void setNoInternetImage(){

        mNoInternetImage.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
    }

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
