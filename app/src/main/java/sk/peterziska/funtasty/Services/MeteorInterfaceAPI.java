package sk.peterziska.funtasty.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sk.peterziska.funtasty.Data.Meteor;

public interface MeteorInterfaceAPI {

    @GET("/resource/y77d-th95.json")
    Call<List<Meteor>> getMeteors(@Query("$$app_token") String appid);
}
