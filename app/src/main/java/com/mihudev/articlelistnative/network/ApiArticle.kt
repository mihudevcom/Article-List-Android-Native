package com.mihudev.articlelistnative.network

import retrofit2.http.GET

data class ApiArticle(
    val id: Int,
    val title: String,
    val body: String
)

interface ArticleApiService {
    @GET("posts")
    suspend fun fetchArticles(): List<ApiArticle>
}