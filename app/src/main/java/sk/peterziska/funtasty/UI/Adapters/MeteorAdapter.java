package sk.peterziska.funtasty.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}