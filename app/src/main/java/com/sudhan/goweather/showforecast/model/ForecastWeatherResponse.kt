package com.sudhan.goweather.showforecast.model

import com.google.gson.annotations.SerializedName

class ForecastWeatherResponse : CurrentWeatherResponse(){
    @SerializedName("forecast")
    lateinit var forecast:Forecast

    class Forecast {
        @SerializedName("forecastday")
        lateinit var forecastday:List<ForecastDay>
    }

    class ForecastDay {
        @SerializedName("date")
        lateinit var date:String
        @SerializedName("day")
        lateinit var day:Day
    }

    class Day {
        @SerializedName("maxtemp_c")
        lateinit var maxTempC:String
        @SerializedName("mintemp_c")
        lateinit var minTempC:String
        @SerializedName("avgtemp_c")
        lateinit var avgTempC:String
        @SerializedName("condition")
        lateinit var condition:Condition
    }
}