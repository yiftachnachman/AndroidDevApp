package com.example.myapplication.view

import android.app.AlarmManager
import android.content.Intent
import android.app.PendingIntent
import android.content.Context
import android.util.Log
import java.util.*
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.Model.Gif
import com.example.myapplication.util.MyAlarmManager
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.util.rotate90
import com.example.myapplication.viewmodel.CountViewModel
import com.example.myapplication.viewmodel.GifViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.atomic.LongAdder


//this activity gets turned on during the most of the time the app exists.
// The main activity has several other functions like onCreate() that you can use for the app.
//This is also like java's main function.
class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel
    private lateinit var gifViewModel: GifViewModel
    private var gcounter: Long = 0
    lateinit var alarmManager: AlarmManager

    private fun getUserName() = intent.extras?.get("username").toString().trim()

//creating a instance of a object and if it previously had data in it, it will still have that data.
// Otherwise it is null.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val calendar = Calendar.getInstance()
      //  calendar.add(Calendar.SECOND, 10)
    //    MyAlarmManager.setAlarm(applicationContext, calendar.timeInMillis, "Test Message!")

    //countViewModel calls in the other activity and turned it on.
    // Then it uses that activity in order to get the amount of users there are and
    // updating the counter of the clicking button.
    // As that number should be different based on which user is logined in at that time.
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUserName()).observe(this,
            androidx.lifecycle.Observer {updateCounter(it) })

        gifViewModel = ViewModelProviders.of(this).get(GifViewModel::class.java)
        gifViewModel.getRandomGif("android").observe(this,
            androidx.lifecycle.Observer {
                Log.d("MainActivity", "before")
                loadGif(it)
                Log.d("MainActivity", "after")
            })

        myButton.setOnClickListener {
            gcounter++
            gcounter--
            myCounter.text = "Counter: " + gcounter.toString()
            myImage.rotate90()

            countViewModel.setUserCount(getUserName(), gcounter + 1)
        }
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmmanagerbutton.setOnClickListener {
            val second = 10 * 1000
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("ALARM_MODE", true)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", " Create : " + Date().toString())
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, pendingIntent)

        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + second, pendingIntent)
        }
    }
    }
    //updateCounter is a function that just makes sure that the counter inside the text box is always
    // at the desired number.
    private fun updateCounter(count: Long){
        gcounter = count
        myCounter.text = gcounter.toString()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


    private fun loadGif(gif: Gif){
        Glide.with(this)
            .load(gif.url)
            .error(R.drawable.nfl)
            .into(myImage)

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