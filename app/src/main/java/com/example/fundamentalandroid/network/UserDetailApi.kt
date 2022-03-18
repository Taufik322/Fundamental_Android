package com.example.fundamentalandroid.network

import com.google.gson.annotations.SerializedName

data class UserDetailApi(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("followers")
    val followers: String,

    @field:SerializedName("following")
    val following: String,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("public_repos")
    val repository: String
)

