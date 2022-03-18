package com.example.fundamentalandroid.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalandroid.network.ApiConfig
import com.example.fundamentalandroid.network.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private var listFollowers = MutableLiveData<List<DataItem>>()

    fun setFollowingData(name: String){
        val client = ApiConfig.getApiService().getFollowing(name)
        client.enqueue(object : Callback<ArrayList<DataItem>> {
            override fun onResponse(
                call: Call<ArrayList<DataItem>>,
                response: Response<ArrayList<DataItem>>
            ) {
                if (response != null){
                    listFollowers.postValue(response.body())
                }else{
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<DataItem>>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowingData(): LiveData<List<DataItem>> {
        return listFollowers
    }
}