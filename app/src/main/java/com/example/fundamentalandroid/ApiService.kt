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