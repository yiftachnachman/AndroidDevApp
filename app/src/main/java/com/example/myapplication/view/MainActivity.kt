package com.example.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.util.rotate90
import com.example.myapplication.viewmodel.CountViewModel
import kotlinx.android.synthetic.main.activity_main.*

//this activity gets turned on during the most of the time the app exists.
// The main activity has several other functions like onCreate() that you can use for the app.
//This is also like java's main function.
class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel
    private var gcounter: Long = 0
    private fun getUserName() = intent.extras?.get("username").toString().trim()
//    fun getStore() = getPreferences(Context.MODE_PRIVATE)
//    var G_COUNTER_KEY: String = ""


//creating a instance of a object and if it previously had data in it, it will still have that data.
// Otherwise it is null.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//val name = intent.extras?.get("username").toString().trim()
//G_COUNTER_KEY = name
//if (savedInstanceState != null) {
//updateCounter(savedInstanceState.getLong(G_COUNTER_KEY, 0))
//} else if (getStore().contains(G_COUNTER_KEY)){
//updateCounter(getStore().getLong(G_COUNTER_KEY, 0))
//}

    //countViewModel calls in the other activity and turned it on.
    // Then it uses that activity in order to get the amount of users there are and
    // updating the counter of the clicking button.
    // As that number should be different based on which user is logined in at that time.
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUserName()).observe(this,
            androidx.lifecycle.Observer {updateCounter(it) })

        myButton.setOnClickListener {
            gcounter++
            myCounter.text = "Counter: " + gcounter.toString()
            myImage.rotate90()
        }
    }
    //updateCounter is a function that just makes sure that the counter inside the text box is always
    // at the desired number.
    private fun updateCounter(count: Long){
        gcounter = count
        myCounter.text = gcounter.toString()
    }
//onPause will make that part of the activity stop until further told to
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