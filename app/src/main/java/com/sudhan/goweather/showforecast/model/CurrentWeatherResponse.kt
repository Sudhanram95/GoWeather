package com.sudhan.goweather.showforecast.model

import com.google.gson.annotations.SerializedName

open class CurrentWeatherResponse {

    @SerializedName("location")
    lateinit var location:Location
    @SerializedName("current")
    lateinit var current:Current

    class Location {
        @SerializedName("name")
        lateinit var name:String
        @SerializedName("region")
        lateinit var region:String
        @SerializedName("country")
        lateinit var country:String
        @SerializedName("condition")
        lateinit var condition:Condition
        @SerializedName("feelslike_c")
        lateinit var feelsLikeC:String
    }

    class Current {
        @SerializedName("temp_c")
        lateinit var tempC:String
        @SerializedName("last_updated")
        lateinit var lastUpdated:String
        @SerializedName("is_day")
        var isDay:Int = 0
        @SerializedName("condition")
        lateinit var condition:Condition
        @SerializedName("feelslike_c")
        lateinit var feelsLikeC:String
        @SerializedName("precip_mm")
        lateinit var precipitation:String
        @SerializedName("uv")
        lateinit var uv:String
    }

    open class Condition {
        @SerializedName("text")
        lateinit var text:String
        @SerializedName("icon")
        lateinit var icon:String
    }
}