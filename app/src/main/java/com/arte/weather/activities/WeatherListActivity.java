package com.arte.weather.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.arte.weather.R;
import com.arte.weather.adapters.WeatherRecyclerViewAdapter;
import com.arte.weather.fragments.WeatherDetailFragment;
import com.arte.weather.model.Weather;
import com.arte.weather.network.GetWeatherListRequest;

import java.util.ArrayList;
import java.util.List;

public class WeatherListActivity extends AppCompatActivity implements WeatherRecyclerViewAdapter.Events, GetWeatherListRequest.Callbacks {

    private boolean mTwoPane;
    private List<Weather> mWeatherList = new ArrayList<>();
    private WeatherRecyclerViewAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        setupActivity();
        loadWeatherList();
    }

    @Override
    public void onWeatherClicked(Weather weather) {
        if (mTwoPane) {
            Bundle fragmentArguments = new Bundle();
            fragmentArguments.putString(WeatherDetailFragment.ARG_WEATHER_ID, weather.getId());
            WeatherDetailFragment fragment = new WeatherDetailFragment();
            fragment.setArguments(fragmentArguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.weather_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, WeatherDetailActivity.class);
            intent.putExtra(WeatherDetailFragment.ARG_WEATHER_ID, weather.getId());
            startActivity(intent);
        }
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.weather_list);
        assert recyclerView != null;
        mAdapter = new WeatherRecyclerViewAdapter(mWeatherList, this, this);
        recyclerView.setAdapter(mAdapter);

        if (findViewById(R.id.weather_detail_container) != null) {
            mTwoPane = true;
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.weather_list_loading));
        mProgressDialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();

        mProgressDialog.dismiss();
    }

    private void loadWeatherList() {
        GetWeatherListRequest request = new GetWeatherListRequest(this, this);
        request.execute();
    }

    @Override
    public void onGetWeatherListSuccess(List<Weather> weatherList) {
        mProgressDialog.hide();
        mWeatherList.clear();
        mWeatherList.addAll(weatherList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetWeatherListError() {
        mProgressDialog.hide();
    }
}
