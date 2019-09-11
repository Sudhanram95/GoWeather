package com.sudhan.goweather.network

import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse
import com.sudhan.goweather.showforecast.presenter.IForecastPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NetworkManager(var iForecastPresnter:IForecastPresenter) {
    var apiEndpoints:ApiEndpoints? = NetworkUtil.retrofitHelper()?.create(ApiEndpoints::class.java)

    fun getCurrentWeatherReport(city:String) {
        apiEndpoints?.getCurrentWeather(NetworkConstants.API_KEY, city)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<CurrentWeatherResponse>() {
                    override fun onSuccess(currentWeatherResponse: CurrentWeatherResponse) {
                        iForecastPresnter.onCurrentWeatherSuccess(currentWeatherResponse)
                    }

                    override fun onError(e: Throwable) {
                       iForecastPresnter.onApiError()
                    }
                })
    }

    fun getForecastWeatherReport(city:String) {
        apiEndpoints?.getForecastWeather(NetworkConstants.API_KEY, city, NetworkConstants.NO_OF_DAYS)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object : DisposableSingleObserver<ForecastWeatherResponse>() {
                    override fun onSuccess(forecastWeatherResponse: ForecastWeatherResponse) {
                        iForecastPresnter.onForecastWeatherSuccess(forecastWeatherResponse)
                    }

                    override fun onError(e: Throwable) {
                        iForecastPresnter.onApiError()
                    }
                })
    }
}