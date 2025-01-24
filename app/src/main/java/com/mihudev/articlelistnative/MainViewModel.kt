package com.mihudev.articlelistnative

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

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            try {
                val apiArticles = RetrofitInstance.api.fetchArticles()
                _articles.value = apiArticles.map { apiArticle ->
                    Article(
                        id = apiArticle.id,
                        title = apiArticle.title,
                        liked = false
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
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