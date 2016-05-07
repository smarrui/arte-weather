package com.arte.weather.network;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arte.weather.Constants;
import com.arte.weather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetWeatherListRequest {

    private static final String URL = "http://api.openweathermap.org/data/2.5/find?lat=" + Constants.LATITUDE + "&lon=" + Constants.LONGITUDE + "&cnt=" + Constants.CITIES_SIZE + "&APPID=" + Constants.API_KEY;

    public interface Callbacks {
        void onGetWeatherListSuccess(List<Weather> weatherList);

        void onGetWeatherListError();
    }

    private Context mContext;
    private Callbacks mCallbacks;

    public GetWeatherListRequest(Context context, Callbacks callbacks) {
        mContext = context;
        mCallbacks = callbacks;
    }

    public void execute() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray citiesJSON = response.getJSONArray("list");
                    List<Weather> weatherCities = new ArrayList<>();
                    for (int i = 0; i < citiesJSON.length(); i++) {
                        Weather weatherCity = new Weather();
                        JSONObject weatherJSON = (JSONObject) citiesJSON.get(i);
                        weatherCity.setId(String.valueOf(weatherJSON.getInt("id")));
                        weatherCity.setName(weatherJSON.getString("name"));
                        JSONArray weatherField = weatherJSON.getJSONArray("weather");
                        if (weatherField != null && weatherField.length() != 0) {
                            weatherCity.setWeatherDetail(((JSONObject)weatherField.get(0)).getString("main"));
                        }
                        weatherCities.add(weatherCity);
                    }
                    mCallbacks.onGetWeatherListSuccess(weatherCities);
                } catch (JSONException e) {
                    Log.e(GetWeatherListRequest.class.getSimpleName(), "Error deserializando JSON", e);
                    mCallbacks.onGetWeatherListError();
                    return;
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mCallbacks.onGetWeatherListError();
            }
        });

        RequestQueueManager.getInstance(mContext).addToRequestQueue(request);
    }
}
