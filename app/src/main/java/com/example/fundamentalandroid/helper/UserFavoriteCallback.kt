package com.example.fundamentalandroid.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.fundamentalandroid.database.UserFavorite

class UserFavoriteCallback(private val mOldFavoriteList: List<UserFavorite>, private val mNewFavoriteList: List<UserFavorite>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteList[oldItemPosition].id == mNewFavoriteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = mOldFavoriteList[oldItemPosition]
        val newFavorite = mNewFavoriteList[newItemPosition]
        return oldFavorite.id == newFavorite.id && oldFavorite.login == newFavorite.login && oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}