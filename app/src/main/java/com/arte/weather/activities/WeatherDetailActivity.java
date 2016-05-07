package com.arte.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arte.weather.R;
import com.arte.weather.fragments.WeatherDetailFragment;

public class WeatherDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        setupActivity();
        setupFragment(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, WeatherListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            return;
        }

        Bundle fragmentArguments = new Bundle();
        fragmentArguments.putString(WeatherDetailFragment.ARG_WEATHER_ID, getIntent().getStringExtra(WeatherDetailFragment.ARG_WEATHER_ID));
        WeatherDetailFragment fragment = new WeatherDetailFragment();
        fragment.setArguments(fragmentArguments);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.weather_detail_container, fragment)
                .commit();

    }
}
