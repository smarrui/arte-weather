package com.arte.weather.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arte.weather.R;
import com.arte.weather.model.Weather;
import com.arte.weather.network.GetWeatherRequest;

public class WeatherDetailFragment extends Fragment implements GetWeatherRequest.Callbacks {

    public static final String ARG_WEATHER_ID = "weather_id";

    private Weather mWeather;
    private ProgressDialog mProgressDialog;
    private TextView mWeatherDetail;
    private TextView mWeatherMoreDetail;

    public WeatherDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_WEATHER_ID)) {
            String weatherId = getArguments().getString(ARG_WEATHER_ID);
            GetWeatherRequest request = new GetWeatherRequest(getActivity(), this, weatherId);
            request.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_detail, container, false);
            mWeatherDetail = (TextView) rootView.findViewById(R.id.item_weather_weather_detail);
        mWeatherMoreDetail = (TextView) rootView.findViewById(R.id.item_weather_weather_more_detail);
        if (mWeather != null) {
            loadWeatherDetails(mWeather);
        }
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(getString(R.string.weather_detail_loading));
        mProgressDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();

        mProgressDialog.dismiss();
    }

    private void loadWeatherDetails(Weather weather) {
        mWeather = weather;
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mWeather.getName());
        }
        mWeatherDetail.setText(mWeather.getWeatherDetail());
        mWeatherMoreDetail.setText(mWeather.getWeatherMoreDetail());
    }

    @Override
    public void onGetWeatherSuccess(Weather weather) {
        loadWeatherDetails(weather);
        mProgressDialog.hide();
    }

    @Override
    public void onGetWeatherError() {
        mProgressDialog.hide();
        Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
    }
}
