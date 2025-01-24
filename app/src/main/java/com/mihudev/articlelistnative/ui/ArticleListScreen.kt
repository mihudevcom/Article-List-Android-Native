package com.mihudev.articlelistnative.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mihudev.articlelistnative.viewmodel.MainViewModel
import com.mihudev.articlelistnative.model.Article


@Composable
fun ArticleListScreen(
    articles: List<Article>,
    onArticleClick: (Int) -> Unit,
    onLikeToggle: (Int) -> Unit,
    viewModel: MainViewModel
) {

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier.padding(16.dp)
    ) {
        items(articles) { article ->
            ArticleRow(
                article = article,
                onLikeToggle = onLikeToggle,
                onArticleClick = onArticleClick
            )
        }
        item {
            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }

    val shouldLoadMore = remember {
        derivedStateOf {
            val visibleItems = listState.layoutInfo.visibleItemsInfo
            val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: -1
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisibleItemIndex >= totalItems - 3 && totalItems > 0
        }
    }

    LaunchedEffect(Unit) {
        if (articles.isEmpty()) viewModel.fetchArticles()
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && !viewModel.isLoading) {
            viewModel.fetchArticles()
        }
    }
}