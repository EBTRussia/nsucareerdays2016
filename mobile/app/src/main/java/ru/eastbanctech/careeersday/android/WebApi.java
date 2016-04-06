package ru.eastbanctech.careeersday.android;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebApi {

    @GET("/")
    Call<Map<String, Boolean>> list();

    @POST("/{resource}")
    Call<Boolean> switchBulb(@Path("resource") String resource, @Body Boolean enabled);
}
