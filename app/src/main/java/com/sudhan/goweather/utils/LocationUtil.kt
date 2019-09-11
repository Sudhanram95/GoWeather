package com.sudhan.goweather.utils

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.sudhan.goweather.getpermission.presenter.IPermissionPresenter

class LocationUtil(var context: Context, var iPermissionPresenter:IPermissionPresenter) : LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private var googleApiClient:GoogleApiClient

    init {
        googleApiClient = initialiseApi()
        settingsrequest()
        googleApiClient.connect()
    }

    fun initialiseApi():GoogleApiClient {
        return GoogleApiClient.Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build()
    }

    fun settingsrequest() {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = (30 * 1000).toLong()
        locationRequest.fastestInterval = (5 * 1000).toLong()
        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build())
        result.setResultCallback { result ->
            val status = result.status
            when (status.statusCode) {
                LocationSettingsStatusCodes.SUCCESS ->
                    iPermissionPresenter.getCurrentCity(googleApiClient)

                LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                    status.startResolutionForResult(context as Activity, 1010)
                } catch (e: IntentSender.SendIntentException) {

                }

                LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                }
            }
        }
    }

    override fun onLocationChanged(location: Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

}