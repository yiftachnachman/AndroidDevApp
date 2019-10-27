package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.CountRepository
//another activity in which there is a repository which is taken from the countRepository activity
// which takes the amount of Users and makes sure those users are still inside the repository
// which is basically a list that tracks the amount of users there are in the app
//NOTICE THERE IS A REASON I DO NOT USE THE ONCREATE function
class CountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountRepository(application.applicationContext)

    fun getUserCount(name: String) = repository.getUserCount(name)

    fun setUserCount(name: String, count: Long) = repository.setUserCount(name,count)
}
