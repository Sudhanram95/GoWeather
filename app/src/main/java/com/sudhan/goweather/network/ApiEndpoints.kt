package com.sudhan.goweather.network

import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {
    @GET("current.json")
    fun getCurrentWeather(@Query("key") apiKey:String, @Query("q") city:String):Single<CurrentWeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(@Query("key") apiKey:String, @Query("q") city:String, @Query("days") days:Int):Single<ForecastWeatherResponse>
}