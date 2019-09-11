package com.sudhan.goweather.showforecast.view

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sudhan.goweather.R
import com.sudhan.goweather.showforecast.model.CurrentWeatherResponse
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse
import com.sudhan.goweather.showforecast.presenter.ForecastPresenter
import com.sudhan.goweather.showforecast.presenter.IForecastPresenter
import com.sudhan.goweather.utils.CommonUtil

class ShowForecastActivity : AppCompatActivity(), IForecastView {

    lateinit var iForecastPresenter:IForecastPresenter
    lateinit var dialog: Dialog
    lateinit var city:String
    lateinit var txtCurrrentTemperature:TextView
    lateinit var txtCity:TextView
    lateinit var rvForecast:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_forecasts)
        city = intent.getStringExtra("city")
        initialiseView()
        iForecastPresenter = ForecastPresenter(this@ShowForecastActivity, this)
        iForecastPresenter.onCurrentWeatherApi(city)
    }

    private fun initialiseView() {
        txtCurrrentTemperature = findViewById(R.id.txt_current_temperature) as TextView
        txtCity = findViewById(R.id.txt_city) as TextView
        rvForecast = findViewById(R.id.rv_forecast_report) as RecyclerView
    }

    override fun onCurrentWeatherSuccessResult(response: CurrentWeatherResponse) {
        iForecastPresenter.onForecastWeatherApi(city)
        txtCity.setText(response.location.name)
        txtCurrrentTemperature.setText(CommonUtil.removeDecimal(response.current.tempC))
    }

    override fun onForecastWeatherSuccessResult(response: ForecastWeatherResponse) {
        dialog.dismiss()
        iForecastPresenter.onBindAdapter(this, rvForecast, response.forecast.forecastday)
        rvForecast.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up))
    }

    override fun onApiErrorResult() {
        dialog = Dialog(this@ShowForecastActivity, android.R.style.Theme_Holo_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.error_dialog)
        val btnRetry = dialog.findViewById<Button>(R.id.btn_retry)
        btnRetry.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
            iForecastPresenter.onApiRetry(city)
        })
        dialog.show()
    }

    override fun onShowLoadingDialogResult() {
        dialog = Dialog(this@ShowForecastActivity, android.R.style.Theme_Holo_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.loading_dialog)
        val imgLoading = dialog.findViewById<ImageView>(R.id.img_loading);
        val rotation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        rotation.setFillAfter(true)
        imgLoading.startAnimation(rotation)
        dialog.show()
    }
}
