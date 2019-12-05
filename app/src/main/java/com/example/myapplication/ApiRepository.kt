package com.example.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Model.Gif
import com.example.myapplication.Model.GifResponse
import com.example.myapplication.Network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log


class ApiRepository(context: Context) {
    private val giphyService: Api = Api.create(context)

    fun getRandomGif(tag: String): LiveData<Gif> {
        val data = MutableLiveData<Gif>()

        giphyService.getRandomGif(tag, "pg", API_KEY).enqueue(object : Callback<GifResponse> {
            override fun onResponse(call: Call<GifResponse>, response: Response<GifResponse>) {
                data.value = response.body()?.data
            }

            override fun onFailure(call: Call<GifResponse>, t: Throwable) {
                Log.d("MainActivity","Failed to create: ${t.localizedMessage} ${t.stackTrace}")
            }
        })

        return data
    }

    companion object {
        private const val API_KEY = "KYlgkhIA7R3fQlwBxuRIw4tco7LLiTaa"
    }
}