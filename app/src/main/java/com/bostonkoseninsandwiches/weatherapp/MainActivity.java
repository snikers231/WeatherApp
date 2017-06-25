package com.bostonkoseninsandwiches.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.bostonkoseninsandwiches.weatherapp.Helpers.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

                //Sending request to OpenweatherMap using Retrofit and WeatherAPI request's constructor.

                Call<WeatherData> call = weatherAPI.getWeatherToday(cityName, "ru", "metric", API_KEY);


                call.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                    //Retreiving data from OpenWeatherMap using Helpers and populating TextViews

                        String temp = String.valueOf(response.body().getMain().getTemp());
                        String humidity = String.valueOf(response.body().getMain().getHumidity());
                        String pressure = String.valueOf(response.body().getMain().getPressure());


                        String message = String.valueOf(response.message());

                        TextView textview1 = (TextView) findViewById(R.id.buton);
                        textview1.setText(message);


                        TextView tempView = (TextView) findViewById(R.id.temp_today);
                        tempView.setText("Температура " + temp + " градусов Цельсия");

                        TextView humView = (TextView) findViewById(R.id.humidity_today);
                        humView.setText("Абсолютная влажность воздуха " + humidity + "%");
                        TextView presView = (TextView) findViewById(R.id.pressure_today);
                        presView.setText("Атмосферное давление " + pressure + " мм.рт.ст.");

                    }


                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {

                    }
                });

            }


        });


    }
}