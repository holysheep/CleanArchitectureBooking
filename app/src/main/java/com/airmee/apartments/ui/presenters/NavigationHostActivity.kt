package com.airmee.apartments.ui.presenters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airmee.apartments.R

class NavigationHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_host)
        supportActionBar?.hide()
    }
}