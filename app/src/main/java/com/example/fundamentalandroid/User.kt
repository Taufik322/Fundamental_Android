package com.example.fundamentalandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String,
    var name: String,
    var profilePicture: Int,
    var repository: String,
    var followers: String,
    var following: String,
    var location: String
): Parcelable
