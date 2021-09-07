package com.ronivaldoroner.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy{
        supportFragmentManager.findFragmentById(R.id.mainFragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment.navController.setGraph(R.navigation.movies_nav_graph, intent.extras)
    }
}