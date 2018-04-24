package sk.peterziska.funtasty.Services;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.util.ArrayList;
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
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://data.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        mMeteors = new ArrayList<>();
        MeteorInterfaceAPI meteorInterfaceAPI = retrofit.create(MeteorInterfaceAPI.class);
        Call<List<Meteor>> call = meteorInterfaceAPI.getMeteors(API_TOKEN);


        call.enqueue(new Callback<List<Meteor>>() {
            @Override
            public void onResponse(Call<List<Meteor>> call, Response<List<Meteor>> response) {
                Log.d("API" , "SUCCESS " + response.body().get(0).getId());
                DatabaseManager.getInstance().saveToDatabase(response.body());
            }

            @Override
            public void onFailure(Call<List<Meteor>> call, Throwable t) {
                Log.d("API" , "ERROR" + t.getMessage());

            }
        });
    }


}