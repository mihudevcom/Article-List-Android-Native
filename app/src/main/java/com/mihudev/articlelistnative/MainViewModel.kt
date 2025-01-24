package com.mihudev.articlelistnative

import androidx.lifecycle.ViewModel
import com.mihudev.articlelistnative.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _articles = MutableStateFlow(
        listOf(
            Article(1, "Article 1", false),
            Article(2, "Article 2", true),
            Article(3, "Article 3", false)
        )
    )

    val articles: StateFlow<List<Article>> = _articles

    fun onLikeToggle(articleId: Int) {
        _articles.value = _articles.value.map { article ->
            if (article.id == articleId) article.copy(liked = !article.liked)
            else article
        }
    }
}