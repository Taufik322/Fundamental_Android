package com.example.fundamentalandroid.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.network.ApiConfig
import com.example.fundamentalandroid.network.UserDetailApi
import com.example.fundamentalandroid.repository.UserFavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) :ViewModel() {

    private val _userDetail = MutableLiveData<UserDetailApi>()
    val userDetailList: LiveData<UserDetailApi> = _userDetail


    private val mUserFavoriteRepository: UserFavoriteRepository = UserFavoriteRepository(application)

    fun addToFavorite(user: UserFavorite) {
        mUserFavoriteRepository.addFavorite(user)
    }

    fun removeFromFavorite(id: Int){
        mUserFavoriteRepository.deleteFavorite(id)
    }

    fun getFavorites(): LiveData<List<UserFavorite>> = mUserFavoriteRepository.getAllUserFavorite()


    fun setUserDetail(name: String){
        val client = ApiConfig.getApiService().getUserDetails(name)
        client.enqueue(object : Callback<UserDetailApi>{
            override fun onResponse(call: Call<UserDetailApi>, response: Response<UserDetailApi>) {
                if (response.isSuccessful){
                    _userDetail.postValue(response.body())
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
        return _userDetail
    }
}