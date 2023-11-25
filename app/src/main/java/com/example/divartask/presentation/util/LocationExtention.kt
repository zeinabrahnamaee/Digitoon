package com.example.divartask.presentation.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun Context.isPermittedLocationAccess(): Boolean {
    return (ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
            )
}


fun Activity.showRequestLocationPermission(requestCode: Int) {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        requestCode
    )
}

fun Context.gpsIsOn(): Boolean {
    val manager = (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
    return (manager.isProviderEnabled(LocationManager.GPS_PROVIDER))

}

fun Activity.buildAlertMessageNoGps() {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
        .setCancelable(false)
        .setPositiveButton(
            "Yes"
        ) { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
        .setNegativeButton(
            "No"
        ) { dialog, _ -> dialog.cancel() }
    val alert: AlertDialog = builder.create()
    alert.setCancelable(true)
    alert.setCanceledOnTouchOutside(true)
    alert.show()
}

fun FusedLocationProviderClient.locationFlow(locationRequest: LocationRequest) =
    callbackFlow {
        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                try {
                    result.lastLocation?.let { trySend(it) }
                } catch (e: Exception) {
                }
            }
        }
        requestLocationUpdates(locationRequest, callback, Looper.getMainLooper())
            .addOnFailureListener { e ->
                close(e) // in case of exception, close the Flow
            }
        // clean up when Flow collection ends
        awaitClose {
            removeLocationUpdates(callback)
        }
    }


fun Boolean?.iF(onTrue: () -> Unit, onFalse: (() -> Unit)? = null) {
    if (this == true) {
        onTrue()
    } else {
        onFalse?.invoke()
    }
}