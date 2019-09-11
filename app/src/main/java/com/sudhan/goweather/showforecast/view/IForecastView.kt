package com.sudhan.goweather.showforecast.view

import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse

interface IForecastView {
    fun onCurrentWeatherSuccessResult(response: CurrentWeatherResponse)
    fun onForecastWeatherSuccessResult(response: ForecastWeatherResponse)
    fun onApiErrorResult()
    fun onShowLoadingDialogResult()
}