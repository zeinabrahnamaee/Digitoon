package com.example.digitoon.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.digitoon.R
import com.example.digitoon.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var baseBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    }

}