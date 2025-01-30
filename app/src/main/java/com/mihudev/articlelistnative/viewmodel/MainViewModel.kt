package com.mihudev.articlelistnative.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihudev.articlelistnative.model.Article
import com.mihudev.articlelistnative.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private var currentPage = 1
    private val pageSize = 10
    internal var isLoading = false
        private set

    init {
        fetchArticles()
    }

    internal fun fetchArticles() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val apiArticles =
                    RetrofitInstance.api.fetchArticles(page = currentPage, limit = pageSize)
                val newArticles = apiArticles.map { apiArticle ->
                    Article(
                        id = apiArticle.id,
                        title = apiArticle.title,
                        liked = false
                    )
                }

                _articles.value += newArticles
                currentPage++
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "failed to load articles. Please try again."
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }


    fun onLikeToggle(articleId: Int) {
        _articles.value = _articles.value.map { article ->
            if (article.id == articleId) article.copy(liked = !article.liked)
            else article
        }
    }
}