package sk.peterziska.funtasty.UI.Activity;

import java.util.List;

import sk.peterziska.funtasty.Data.Meteor;

public interface MeteorActivityInt {
    void showProgressBar();
    void setNoInternetImage();
    void hideProgressBar();
    void setSumMeteorsTextView(String sum);
    void setMeteorRecyclerView(List<Meteor> data);
}
