package com.example.fundamentalandroid.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalandroid.network.ApiConfig
import com.example.fundamentalandroid.network.DataItem
import com.example.fundamentalandroid.network.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val listUser = MutableLiveData<List<DataItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findUsers(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsersSearch(name)
        client.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    listUser.postValue(response.body()?.items)
                } else {
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    fun getFindUsers(): LiveData<List<DataItem>> {
        return listUser
    }

}