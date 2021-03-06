package com.bostonkoseninsandwiches.weatherapp;

import com.bostonkoseninsandwiches.weatherapp.Helpers.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Konstantin2 on 24.06.2017.
 */

public interface WeatherAPI {
//root is not a list
    @GET("weather")
    Call<WeatherData> getWeatherToday(@Query("q") String name,
                                      @Query("lang") String lang,
                                      @Query("units") String units,
                                      @Query("apiKey") String apiKey);

}
