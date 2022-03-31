package com.example.fundamentalandroid.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.database.UserFavoriteDao
import com.example.fundamentalandroid.database.UserFavoriteRoomDatabase
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserFavoriteRepository(application: Application) {
    private val mUserFavoriteDao: UserFavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserFavoriteRoomDatabase.getDatabase(application)
        mUserFavoriteDao = db.userFavoriteDao()
    }

    fun getAllUserFavorite(): LiveData<List<UserFavorite>> = mUserFavoriteDao.getUserFavorite()

    fun addFavorite(userFavorite: UserFavorite){
        executorService.execute { mUserFavoriteDao.addFavorite(userFavorite) }
    }

    fun deleteFavorite(id: Int){
        executorService.execute { mUserFavoriteDao.deleteFavorite(id) }
    }
}