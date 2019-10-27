package com.example.myapplication
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import android.content.Context
import android.content.SharedPreferences
import me.ibrahimsn.library.LiveSharedPreferences
//this activity exists in order to share the information between activities when the app is
// either switching users and just starting up again
class CountRepository(context: Context) {
    private val preferences: SharedPreferences
    private val liveSharedPreferences: LiveSharedPreferences

    init {
        preferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        liveSharedPreferences = LiveSharedPreferences(preferences)
    }
//function to set the user count
    fun setUserCount(name: String, count: Long) {
        preferences.edit().putLong(name, count).apply()
    }
//function to get the amount of users
    fun getUserCount(name: String): LiveData<Long> =
        Transformations.map(liveSharedPreferences.listenMultiple(listOf(name),0L)) {it[name]}
//tells the app what variables we wanted to be connected from the last time to this new time.
    companion object {
        private const val PREFS = "clickCounts"
    }
}
