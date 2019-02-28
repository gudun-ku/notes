package com.beloushkin.android.learn.notes.navigation

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.beloushkin.android.learn.notes.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_tasks -> {
                messageTextView!!.setText(R.string.title_task)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notes -> {
                messageTextView!!.setText(R.string.title_note)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        messageTextView.text = getString(R.string.title_task)
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
