package com.example.laboratorio7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.laboratorio7.databinding.ActivityMainBinding


import com.google.android.material.navigation.NavigationView

/**
 * @author Bryann Alfaro
 * Referencias:
 * https://classroom.udacity.com/courses/ud9012
 * https://stackoverflow.com/questions/55307761/create-viewmodel-with-application
 *https://www.journaldev.com/21081/android-viewmodel#android-sqlite
 * Ayuda de: Brandon Hernandez y Andrea Amaya
 * @since 23 February 2020
 */

@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navDrawer: NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding for the main
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )

        //instatiate the drawer and nav
        drawerLayout=binding.drawerLayout
        navDrawer=binding.navView1

        //Controller for the nav
        val Controllernav=this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,Controllernav,drawerLayout)
        NavigationUI.setupWithNavController(navDrawer,Controllernav)

    }
    //Support for the navigate
    override fun onSupportNavigateUp(): Boolean {
        val navController=this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController,drawerLayout)
    }
}
