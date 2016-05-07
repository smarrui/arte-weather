package com.arte.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arte.weather.R;
import com.arte.weather.model.Weather;

import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    public interface Events {
        void onWeatherClicked(Weather weather);
    }

    private final List<Weather> mWeatherList;
    private Events mEvents;
    private Context mContext;

    public WeatherRecyclerViewAdapter(List<Weather> items, Context context, Events events) {
        mWeatherList = items;
        mContext = context;
        mEvents = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Weather weather = mWeatherList.get(position);
        holder.mTitle.setText(weather.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEvents.onWeatherClicked(weather);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTitle;

        public ViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.weather_name);
        }
    }
}
