package com.example.fundamentalandroid.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserFavoriteDao {
    @Insert
    fun addFavorite(userFavorite: UserFavorite)

    @Query("DELETE FROM UserFavorite WHERE UserFavorite.id = :id")
    fun deleteFavorite(id: Int)

    @Query("SELECT * FROM UserFavorite ORDER BY id ASC")
    fun getUserFavorite(): LiveData<List<UserFavorite>>
}