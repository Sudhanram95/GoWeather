package com.sudhan.goweather.getpermission.model

class CityModel(city:String) {
    var cityName:String = ""
    var isValid:Boolean = false

    init {
        cityName = city
    }

    fun checkValidity():Boolean {
        return cityName.length > 0
    }
}