package com.csstudent.manager.api

import com.csstudent.manager.data.GithubRepository
import com.csstudent.manager.data.GithubUser
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<GithubUser>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<List<GithubRepository>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GithubApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(GithubApiService::class.java)
        }
    }
}
