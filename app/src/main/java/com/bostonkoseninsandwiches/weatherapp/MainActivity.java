package com.bostonkoseninsandwiches.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bostonkoseninsandwiches.weatherapp.Helpers.WeatherData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final  String URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "be7dee010e120a3af05f191d88798bde";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText city = (EditText) findViewById(R.id.input_city);



        String cityName = city.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Call<WeatherData> call = weatherAPI.getWeatherToday("Moscow", "ru", API_KEY);

      call.enqueue(new Callback<WeatherData>() {
          @Override
          public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

            //TODO save data to object & display
                String res = String.valueOf(response.body().getCity().getName());
              TextView textview = (TextView) findViewById(R.id.buton);
              textview.setText(res);

          }


          @Override
          public void onFailure(Call<WeatherData> call, Throwable t) {

          }
      });
}
}