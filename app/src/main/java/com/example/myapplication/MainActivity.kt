package com.example.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.content.Context
import android.os.PersistableBundle
import android.view.View
import com.example.myapplication.util.rotate90
import com.example.myapplication.util.toggleVisibility
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var gcounter: Long = 0
    fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            gcounter = savedInstanceState.getLong(G_COUNTER_KEY, 0)
            myCounter.text = gcounter.toString()
        }
        myButton.setOnClickListener {
            gcounter++
            myCounter.text = "Counter: " + gcounter.toString()
            myImage.rotate90()
        }
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(G_COUNTER_KEY, gcounter).apply()
    }
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putLong(G_COUNTER_KEY, gcounter)
        }

        super.onSaveInstanceState(outState)

    }

    companion object {
        private const val G_COUNTER_KEY = "GCounterKey"
    }
}