package com.example.fundamentalandroid

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsersSearch(
        @Query("q") query: String
    ): Call<UsersResponse>

}