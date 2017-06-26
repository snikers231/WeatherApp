package com.bostonkoseninsandwiches.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.bostonkoseninsandwiches.weatherapp.Helpers.WeatherData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "be7dee010e120a3af05f191d88798bde";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText city = (EditText) findViewById(R.id.input_city);


        ImageButton button = (ImageButton) findViewById(R.id.refresh_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cityName = String.valueOf(city.getText());

                //Checking if EditText isn't empty

                if (TextUtils.isEmpty(cityName)) {
                    city.setError("Введите город");
                    return;
                }

                //Sending request to OpenweatherMap using Retrofit and WeatherAPI request's constructor.

                Call<WeatherData> call = weatherAPI.getWeatherToday(cityName, "ru", "metric", API_KEY);


                call.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                        //Retreiving data from OpenWeatherMap using Helpers and populating TextViews

                        String temp = String.valueOf(response.body().getMain().getTemp());
                        String humidity = String.valueOf(response.body().getMain().getHumidity());
                        String pressure = String.valueOf(response.body().getMain().getPressure());
                        String weatherMain = String.valueOf(response.body().getWeather().get(0).getDescription());
                        String windSpeed = String.valueOf(response.body().getWind().getSpeed());


                        //Receiving date from Server
                        long milliseconds = response.body().getDt();
                        Date d = new Date(milliseconds * 1000);
                        SimpleDateFormat simpleData = new SimpleDateFormat("dd.MM.yyyy");
                        String date = simpleData.format(d);


                        String message = String.valueOf(response.message());

                        TextView textview1 = (TextView) findViewById(R.id.buton);
                        textview1.setText(message);


                        TextView tempView = (TextView) findViewById(R.id.temp_today);
                        tempView.setText("Температура " + temp + "°C");

                        TextView humView = (TextView) findViewById(R.id.humidity_today);
                        humView.setText(humidity + "%");

                        TextView presView = (TextView) findViewById(R.id.pressure_today);
                        presView.setText(pressure + " мм.рт.ст.");

                        TextView dateView = (TextView) findViewById(R.id.day_today);
                        dateView.setText("Сегодня " + date);


                        TextView descriptionView = (TextView) findViewById(R.id.description_today);
                        descriptionView.setText(weatherMain);

                        TextView windSpeedView = (TextView) findViewById(R.id.windSpeed_today);
                        windSpeedView.setText(windSpeed + " м/c");

                    }


                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {

                    }
                });

            }


        });


    }
}