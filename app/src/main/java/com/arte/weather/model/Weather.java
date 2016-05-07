package com.arte.weather.model;


public class Weather {

    private String id;
    private String name;
    private String weatherDetail;
    private String weatherMoreDetail;
    private String temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;

    public Weather() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherDetail() {
        return weatherDetail;
    }

    public void setWeatherDetail(String weatherDetail) {
        this.weatherDetail = weatherDetail;
    }

    public String getWeatherMoreDetail() {
        return weatherMoreDetail;
    }

    public void setWeatherMoreDetail(String weatherMoreDetail) {
        this.weatherMoreDetail = weatherMoreDetail;
    }
}
