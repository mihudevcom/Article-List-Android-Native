package com.mihudev.articlelistnative.ui

import androidx.compose.runtime.Composable
import com.mihudev.articlelistnative.model.Article


@Composable
fun ArticleListScreen(
    articles: List<Article>,
    onArticleClick: (Int) -> Unit,
    onLikeToggle: (Int) -> Unit
) {
    ArticleList(
        articles = articles,
        onLikeToggle = onLikeToggle,
        onArticleClick = onArticleClick
    )
}