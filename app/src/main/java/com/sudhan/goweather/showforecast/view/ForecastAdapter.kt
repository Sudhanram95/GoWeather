package com.sudhan.goweather.showforecast.view

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sudhan.goweather.R
import com.sudhan.goweather.showforecast.model.ForecastWeatherResponse
import com.sudhan.goweather.utils.CommonUtil

class ForecastAdapter(var forecastList:List<ForecastWeatherResponse.ForecastDay>) : RecyclerView.Adapter<ForecastAdapter.Companion.MyViewHolder>() {

    companion object {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
        {
            var txtDay:TextView
            var txtTemperature:TextView
            init {
                txtDay = view.findViewById(R.id.txt_day)
                txtTemperature = view.findViewById(R.id.txt_temperature)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.forecast_items, parent, false)
        val myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.txtDay.text = (CommonUtil.getDayOfWeek(forecastList.get(position).date))
        holder.txtTemperature.text = ("${CommonUtil.removeDecimal(forecastList.get(position).day.avgTempC)} C")
    }
}