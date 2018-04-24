package sk.peterziska.funtasty.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import sk.peterziska.funtasty.Data.DatabaseManager;
import sk.peterziska.funtasty.Data.Meteor;
import sk.peterziska.funtasty.R;

public class MeteorAdapter extends RecyclerView.Adapter<MeteorAdapter.CustomViewHolder> {

    List<Meteor> mMeteorList;


    public MeteorAdapter(List<Meteor> meteors){
        mMeteorList = meteors;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meteor_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(mMeteorList.get(position).getName());
        holder.size.setText(String.valueOf(mMeteorList.get(position).getMass()));
        Date date = mMeteorList.get(position).getYear();
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(date);
        holder.date.setText(currentDateTimeString);
    }

    @Override
    public int getItemCount() {
        return mMeteorList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_text_view)
        TextView name;

        @BindView(R.id.size_text_view)
        TextView size;

        @BindView(R.id.date_text_view)
        TextView date;


        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}