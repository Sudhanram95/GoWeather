package com.sudhan.goweather.showforecast.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sudhan.goweather.network.NetworkManager
import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse
import com.sudhan.goweather.showforecast.view.ForecastAdapter
import com.sudhan.goweather.showforecast.view.IForecastView
import com.sudhan.goweather.showforecast.view.ShowForecastActivity
import java.text.SimpleDateFormat
import java.util.*

class ForecastPresenter(var context:Context, var iForecastView:IForecastView) : IForecastPresenter {

    override fun onCurrentWeatherApi(city: String) {
        iForecastView.onShowLoadingDialogResult()
        NetworkManager(this).getCurrentWeatherReport(city)
    }

    override fun onForecastWeatherApi(city: String) {
        NetworkManager(this).getForecastWeatherReport(city)
    }

    override fun onCurrentWeatherSuccess(response: CurrentWeatherResponse) {
        iForecastView.onCurrentWeatherSuccessResult(response)
    }

    override fun onForecastWeatherSuccess(response: ForecastWeatherResponse) {
        iForecastView.onForecastWeatherSuccessResult(response)
    }

    override fun onApiError() {
        iForecastView.onApiErrorResult()
    }

    override fun onApiRetry(city:String) {
        val intent = Intent(context, ShowForecastActivity::class.java)
        intent.putExtra("city", city)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    override fun onBindAdapter(context: Context, recyclerView: RecyclerView, forecastList: List<ForecastWeatherResponse.ForecastDay>) {
        val filteredList = forecastList.filter { v -> !v.date.equals(SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().time)) }
        val forecastAdapter = ForecastAdapter(filteredList)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setAdapter(forecastAdapter)
    }
}