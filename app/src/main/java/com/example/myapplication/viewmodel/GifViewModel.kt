package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.ApiRepository

class GifViewModel(application: Application) : AndroidViewModel(application){
    private val repository = ApiRepository(application.applicationContext)

    fun getRandomGif(tag: String) = repository.getRandomGif(tag)

}