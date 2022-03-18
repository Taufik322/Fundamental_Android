package com.example.fundamentalandroid.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalandroid.network.ApiConfig
import com.example.fundamentalandroid.network.UserDetailApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel :ViewModel() {

    private val userDetail = MutableLiveData<UserDetailApi>()

    fun setUserDetail(name: String){
        val client = ApiConfig.getApiService().getUserDetails(name)
        client.enqueue(object : Callback<UserDetailApi>{
            override fun onResponse(call: Call<UserDetailApi>, response: Response<UserDetailApi>) {
                if (response.isSuccessful){
                    userDetail.postValue(response.body())
                } else {
                    Log.e("UserDetailActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetailApi>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun getUserDetail(): LiveData<UserDetailApi>{
        return userDetail
    }
}