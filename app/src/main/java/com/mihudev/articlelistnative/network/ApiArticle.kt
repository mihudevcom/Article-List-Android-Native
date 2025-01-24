package com.mihudev.articlelistnative.network

import retrofit2.http.GET
import retrofit2.http.Query

data class ApiArticle(
    val id: Int,
    val title: String,
    val body: String
)

interface ArticleApiService {
    @GET("posts")
    suspend fun fetchArticles(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<ApiArticle>
}