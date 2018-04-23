package sk.peterziska.funtasty.Data.Services;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sk.peterziska.funtasty.Data.DatabaseManager;
import sk.peterziska.funtasty.Data.Meteor;

public class MeteorAPI {

    private static final String API_TOKEN = "BBdRUnVA9wDQEpV2MXhEFTA0s";

    private List<Meteor> mMeteors;
    public MeteorAPI(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://data.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        MeteorInterfaceAPI meteorInterfaceAPI = retrofit.create(MeteorInterfaceAPI.class);
        Call<List<Meteor>> call = meteorInterfaceAPI.getMeteors(API_TOKEN);

        call.enqueue(new Callback<List<Meteor>>() {
            @Override
            public void onResponse(Call<List<Meteor>> call, Response<List<Meteor>> response) {
                Log.d("API" , "SUCCESS " + response.body().get(0).getId());
                DatabaseManager.getInstance().saveToDatabase(response.body());
                mMeteors = response.body();
            }

            @Override
            public void onFailure(Call<List<Meteor>> call, Throwable t) {
                Log.d("API" , "ERROR" + t.getMessage());

            }
        });
    }

    public List<Meteor> getMeteors(){
        return mMeteors;
    }
}
