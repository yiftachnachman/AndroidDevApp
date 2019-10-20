package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.util.rotate90
import com.example.myapplication.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel
    private var gcounter: Long = 0
    private fun getUserName() = intent.extras?.get("username").toString().trim()
//    fun getStore() = getPreferences(Context.MODE_PRIVATE)
//    var G_COUNTER_KEY: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val name = intent.extras?.get("username").toString().trim()
//        G_COUNTER_KEY = name
//
//        if (savedInstanceState != null) {
//            updateCounter(savedInstanceState.getLong(G_COUNTER_KEY, 0))
//        } else if (getStore().contains(G_COUNTER_KEY)){
//            updateCounter(getStore().getLong(G_COUNTER_KEY, 0))
//        }

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUserName()).observe(this,
            androidx.lifecycle.Observer {updateCounter(it) })

        myButton.setOnClickListener {
            gcounter++
            myCounter.text = "Counter: " + gcounter.toString()
            myImage.rotate90()
        }
    }
    private fun updateCounter(count: Long){
        gcounter = count
        myCounter.text = gcounter.toString()
    }

//    override fun onPause() {
//        super.onPause()
//        getStore().edit().putLong(G_COUNTER_KEY, gcounter).apply()
//    }
//    override fun onSaveInstanceState(outState: Bundle?) {
//        outState?.run {
//            putLong(G_COUNTER_KEY, gcounter)
//        }
//
//        super.onSaveInstanceState(outState)
//
//    }

}