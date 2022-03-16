package com.example.fundamentalandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val login: String,
    val avatar: String
) : Parcelable
