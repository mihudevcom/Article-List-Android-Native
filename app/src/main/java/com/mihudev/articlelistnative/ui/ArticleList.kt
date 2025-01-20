package com.mihudev.articlelistnative.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mihudev.articlelistnative.model.Article


@Composable
fun ArticleList(articles: List<Article>) {
    LazyColumn {
        items(articles) { article ->
            ArticleRow(article = article)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleList() {
    val sampleArticles = listOf(
        Article(1, "Article 1", false),
        Article(2, "Article 2", true),
        Article(3, "Article 3", false)
    )
    ArticleList(sampleArticles)
}
