package com.example.fundamentalandroid.model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.repository.UserFavoriteRepository

class UserFavoriteViewModel(application: Application): ViewModel() {
    private val mUserFavoriteRepository: UserFavoriteRepository = UserFavoriteRepository(application)

    fun getAllUserFavorite(): LiveData<List<UserFavorite>> = mUserFavoriteRepository.getAllUserFavorite()
}