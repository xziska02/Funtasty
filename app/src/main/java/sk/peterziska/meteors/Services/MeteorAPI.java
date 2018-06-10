package sk.peterziska.meteors.Services;

import android.support.annotation.NonNull;
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
import sk.peterziska.meteors.Data.DatabaseManager;
import sk.peterziska.meteors.Data.Meteor;

public class MeteorAPI {

    private static final String API_TOKEN = "BBdRUnVA9wDQEpV2MXhEFTA0s";
    private static MeteorAPI instance;
    private Gson gson;
    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private MeteorInterfaceAPI meteorInterfaceAPI;

    private MeteorAPI(){
        setRetrofit();
    }

    public static MeteorAPI getInstance(){
        if (instance == null){
            instance = new MeteorAPI();
        }
        return instance;
    }

    public void fetchData(){

        Call<List<Meteor>> call = meteorInterfaceAPI.getMeteors(API_TOKEN);
        call.enqueue(new Callback<List<Meteor>>() {

            @Override
            public void onResponse(@NonNull Call<List<Meteor>> call, @NonNull Response<List<Meteor>> response) {      //save to database whole response
                DatabaseManager.getInstance().saveToDatabase(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meteor>> call, @NonNull Throwable t) {
                Log.d("API" , "ERROR" + t.getMessage());
            }
        });
    }

    private void setRetrofit(){

        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();

        builder = new Retrofit.Builder()
                .baseUrl("https://data.nasa.gov/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        retrofit = builder.build();
        meteorInterfaceAPI = retrofit.create(MeteorInterfaceAPI.class);
    }
}
