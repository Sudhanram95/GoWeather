package com.sudhan.goweather.showforecast.presenter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse

interface IForecastPresenter {
    fun onCurrentWeatherApi(city:String)
    fun onForecastWeatherApi(city:String)
    fun onCurrentWeatherSuccess(response:CurrentWeatherResponse)
    fun onForecastWeatherSuccess(response: ForecastWeatherResponse)
    fun onApiError()
    fun onApiRetry(city:String)
    fun onBindAdapter(context:Context, recyclerView: RecyclerView, foreCastList: List<ForecastWeatherResponse.ForecastDay>)
}