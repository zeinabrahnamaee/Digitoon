package com.example.divartask.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.divartask.R
import com.example.divartask.databinding.ActivityMainBinding
import com.example.divartask.presentation.util.buildAlertMessageNoGps
import com.example.divartask.presentation.util.gpsIsOn
import com.example.divartask.presentation.util.isPermittedLocationAccess
import com.example.divartask.presentation.util.showRequestLocationPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var baseBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gpsIsOn().takeIf { it }?.let {
            isPermittedLocationAccess().takeIf { it }?.let {
                Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()
            } ?: requestLocationPermission()
        } ?: turnOnGps()

        baseBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(baseBinding.root)
        initializeNavigationGraph()
    }

    private fun initializeNavigationGraph() {
        val myNavHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)
        myNavHostFragment.navController.graph = graph
    }

    override fun onResume() {
        super.onResume()
    }


    private fun turnOnGps() {
        this.buildAlertMessageNoGps()
    }

    private fun requestLocationPermission() {
        this.showRequestLocationPermission(MY_PERMISSIONS_REQUEST_LOCATION)
    }

    open fun test(){

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show()

                }

                return
            }
        }
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

}