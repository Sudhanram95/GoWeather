package com.sudhan.goweather.getpermission.presenter

import android.app.Dialog
import com.google.android.gms.common.api.GoogleApiClient

interface IPermissionPresenter {
    fun onPermissionButtonClicked()
    fun onPermissionGranted(dialog:Dialog?)
    fun onPermissionDenied(dialog:Dialog)
    fun getCurrentCity(googleApiClient: GoogleApiClient)
    fun onNextButtonClicked(city:String)
}