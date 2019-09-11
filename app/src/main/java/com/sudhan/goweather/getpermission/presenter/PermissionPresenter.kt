package com.sudhan.goweather.getpermission.presenter

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.support.v4.app.ActivityCompat.requestPermissions
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.sudhan.goweather.getpermission.model.CityModel
import com.sudhan.goweather.getpermission.view.IPermissionView
import com.sudhan.goweather.getpermission.view.PermissionActivity
import com.sudhan.goweather.utils.LocationUtil
import java.util.*

class PermissionPresenter(var context:Context, var iPermissionView:IPermissionView) : IPermissionPresenter {

    override fun onPermissionButtonClicked() {
        requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1000)
    }

    override fun onPermissionGranted(dialog: Dialog?) {
        dialog?.let { dialog.dismiss()  }
        LocationUtil(context as Activity, this)
    }

    override fun onPermissionDenied(dialog:Dialog) {
        dialog.dismiss()
        Toast.makeText(context, "Denied", Toast.LENGTH_SHORT).show()
    }

    override fun getCurrentCity(googleApiClient: GoogleApiClient) {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(isGPSEnabled) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient)
                if(location != null) {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    iPermissionView.onPermissionActivityResult(addresses[0].locality)
                }
            }
        }
    }

    override fun onNextButtonClicked(city: String) {
        var model = CityModel(city)
        if(model.checkValidity()) {
            iPermissionView.onPermissionActivityResult(city)
        } else {
            Toast.makeText(context, "Enter the City field", Toast.LENGTH_SHORT).show()
        }
    }
}