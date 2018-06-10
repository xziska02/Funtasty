package sk.peterziska.meteors.UI.Activity;

import java.util.List;

import sk.peterziska.meteors.Data.Meteor;

public interface MeteorActivityInt {
    void showProgressBar();
    void setNoInternetImage();
    void hideProgressBar();
    void setSumMeteorsTextView(String sum);
    void setMeteorRecyclerView(List<Meteor> data);
}
