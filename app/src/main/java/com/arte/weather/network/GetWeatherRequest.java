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

public class GetWeatherRequest {

    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?id={id}&units=metric&appid=" + Constants.API_KEY;

    public interface Callbacks {
        void onGetWeatherSuccess(Weather weather);

        void onGetWeatherError();
    }

    private Context mContext;
    private Callbacks mCallbacks;
    private String mId;

    public GetWeatherRequest(Context context, Callbacks callbacks, String id) {
        mContext = context;
        mCallbacks = callbacks;
        mId = id;
    }

    public void execute() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getUrl(mId), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Weather weather = new Weather();
                try {
                    weather.setId(String.valueOf(response.getInt("id")));
                    weather.setName(response.getString("name"));
                    JSONArray weatherField = response.getJSONArray("weather");
                    if (weatherField != null && weatherField.length() != 0) {
                        weather.setWeatherDetail(((JSONObject)weatherField.get(0)).getString("main"));
                        weather.setWeatherMoreDetail(((JSONObject)weatherField.get(0)).getString("description"));
                    }
                    JSONObject mainField = response.getJSONObject("main");
                    if (mainField != null) {
                        weather.setTemperature(mainField.getString("temp"));
                        weather.setPressure(mainField.getString("pressure"));
                        weather.setHumidity(mainField.getString("humidity"));
                    }
                    JSONObject windField = response.getJSONObject("wind");
                    if (windField != null) {
                        weather.setWindSpeed(windField.getString("speed"));
                    }
                } catch (JSONException e) {
                    Log.e(GetWeatherListRequest.class.getSimpleName(), "Error deserializando JSON", e);
                    mCallbacks.onGetWeatherError();
                    return;
                }
                mCallbacks.onGetWeatherSuccess(weather);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                mCallbacks.onGetWeatherError();
            }
        });

        RequestQueueManager.getInstance(mContext).addToRequestQueue(request);
    }

    private String getUrl(String id) {
        return URL.replace("{id}", id);
    }
}
