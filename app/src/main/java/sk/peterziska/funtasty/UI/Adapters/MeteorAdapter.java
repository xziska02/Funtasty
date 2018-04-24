package sk.peterziska.funtasty.UI.Adapters;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.RealmList;
import sk.peterziska.funtasty.Data.DatabaseManager;
import sk.peterziska.funtasty.Data.Geolocation;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.R;
import sk.peterziska.funtasty.UI.Activity.MapActivity;

public class MeteorAdapter extends RecyclerView.Adapter<MeteorAdapter.CustomViewHolder> {

    private List<Meteor> mMeteorList;
    private Context mContext;

    public MeteorAdapter(Context context, List<Meteor> meteors){
        mMeteorList = meteors;
        mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meteor_item, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(rootView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(mMeteorList.get(position).getName());
        holder.size.setText(String.valueOf(mMeteorList.get(position).getMass()));

        Date date = mMeteorList.get(position).getYear();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDateTimeString = mdformat.format(date);
        holder.date.setText(currentDateTimeString);

    }

    @Override
    public int getItemCount() {
        return mMeteorList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.name_text_view)
        TextView name;

        @BindView(R.id.size_text_view)
        TextView size;

        @BindView(R.id.date_text_view)
        TextView date;


        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View v) {
            Log.d("CLICKED","ITEM");

            Integer position = getAdapterPosition();
            Intent intent = new Intent(mContext,MapActivity.class);
            Geolocation geo = mMeteorList.get(position).getGeolocation();
            String name = mMeteorList.get(position).getName();

            intent.putExtra("longitude",geo.getCoordinates().get(0));
            intent.putExtra("latitude",geo.getCoordinates().get(1));
            intent.putExtra("name",name);

            mContext.startActivity(intent);
        }
    }
}