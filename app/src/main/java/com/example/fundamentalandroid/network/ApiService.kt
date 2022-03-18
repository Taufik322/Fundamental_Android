package com.example.fundamentalandroid.network

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