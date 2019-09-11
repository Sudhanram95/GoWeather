package com.sudhan.goweather.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.*

object CommonUtil {

    fun isLocationPermissionGranted(context:Context):Boolean{
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    fun removeDecimal(input:String) : String {
        val splitted = input.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return splitted[0]
    }

    fun getDayOfWeek(inputDate:String) :String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.ENGLISH)
        val date = simpleDateFormat.parse(inputDate)
        simpleDateFormat.applyPattern("EEEE")
        return simpleDateFormat.format(date)
    }
}