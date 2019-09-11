package com.sudhan.goweather.getpermission.view

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import com.sudhan.goweather.R
import com.sudhan.goweather.getpermission.presenter.IPermissionPresenter
import com.sudhan.goweather.getpermission.presenter.PermissionPresenter
import com.sudhan.goweather.showforecast.view.ShowForecastActivity
import com.sudhan.goweather.utils.CommonUtil

class PermissionActivity : AppCompatActivity(), IPermissionView {

    lateinit var iPermissionPresenter:IPermissionPresenter
    lateinit var dialog: Dialog
    lateinit var edtCity:EditText
    lateinit var relNext:RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        iPermissionPresenter = PermissionPresenter(this@PermissionActivity, this)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!CommonUtil.isLocationPermissionGranted(this@PermissionActivity))
                showLocationPermissionDialog()
            else
                iPermissionPresenter.onPermissionGranted(null)
        } else {
            iPermissionPresenter.onPermissionGranted(null)
        }
        initializeView()
    }

    private fun initializeView() {
        edtCity = findViewById(R.id.edt_city) as EditText
        relNext = findViewById(R.id.rel_next) as RelativeLayout

        relNext.setOnClickListener(View.OnClickListener {
            iPermissionPresenter.onNextButtonClicked(edtCity.text.toString())
        })
    }

    private fun showLocationPermissionDialog() {
        dialog = Dialog(this@PermissionActivity, android.R.style.Theme_Holo_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.location_permission_dialog)
        val btnGrandPermission = dialog.findViewById<Button>(R.id.btn_grand_permission)
        btnGrandPermission.setOnClickListener(View.OnClickListener {
            iPermissionPresenter.onPermissionButtonClicked()
        })
        dialog.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            iPermissionPresenter.onPermissionGranted(dialog)
        } else{
            iPermissionPresenter.onPermissionDenied(dialog)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1010) {
            iPermissionPresenter.onPermissionGranted(null)
        }
    }

    override fun onPermissionActivityResult(city:String) {
        var intent = Intent(this@PermissionActivity , ShowForecastActivity::class.java)
        intent.putExtra("city", city)
        startActivity(intent)
        finish()
    }
}
