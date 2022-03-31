package com.example.fundamentalandroid.network

import com.example.fundamentalandroid.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsersSearch(
        @Query("q") query: String
    ): Call<UsersResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetails(
        @Path("username") uname: String
    ): Call<UserDetailApi>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") name: String
    ): Call<ArrayList<DataItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") name: String
    ): Call<ArrayList<DataItem>>
}