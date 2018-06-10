package sk.peterziska.meteors.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sk.peterziska.meteors.Data.Meteor;

public interface MeteorInterfaceAPI {

    @GET("/resource/y77d-th95.json?$where=year >= '2011-01-01T00:00:00'")
    Call<List<Meteor>> getMeteors(@Query("$$app_token") String appid);
}
