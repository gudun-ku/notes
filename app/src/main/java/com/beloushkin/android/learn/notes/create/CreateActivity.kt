package com.beloushkin.android.learn.notes.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.beloushkin.android.learn.notes.R
import com.beloushkin.android.learn.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {
            textView.text = if (this == NavigationActivity.FRAGMENT_VALUE_TASK) {
                "this is a task"
            } else {
                "this is a note"
            }
        }

    }

}
