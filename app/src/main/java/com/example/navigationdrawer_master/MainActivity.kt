package com.example.navigationdrawer_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.navigationdrawer_master.fragments.ChatFragment
import com.example.navigationdrawer_master.fragments.MessageFragment
import com.example.navigationdrawer_master.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        var toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        //toggle drawer arrow
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MessageFragment()).commit()
            navView.setCheckedItem(R.id.nav_message)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_message -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MessageFragment()).commit()
            R.id.nav_chat -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ChatFragment()).commit()
            R.id.nav_profile -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ProfileFragment()).commit()
            R.id.nav_send -> Toast.makeText(this@MainActivity, "send clicked...", Toast.LENGTH_LONG).show()
            R.id.nav_share -> Toast.makeText(this@MainActivity, "share clicked...", Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}