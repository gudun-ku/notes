package com.beloushkin.android.learn.notes.navigation

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.notes.NotesListFragment
import com.beloushkin.android.learn.notes.tasks.TasksListFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->
        when (item.itemId) {
            R.id.navigation_tasks -> {
                replaceFragment(TasksListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notes -> {
                replaceFragment(NotesListFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        replaceFragment(TasksListFragment.newInstance())

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, fragment)
            .commit()
    }
}
