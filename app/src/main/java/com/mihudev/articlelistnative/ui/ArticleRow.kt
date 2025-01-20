package com.mihudev.articlelistnative.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihudev.articlelistnative.model.Article


@Composable
fun ArticleRow(
    article: Article,
    onLikeToggle: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = article.title)
        IconButton(onClick = { onLikeToggle(article.id) }) {
            Icon(
                imageVector = if (article.liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Like Button"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleRow() {
    ArticleRow(Article(1, "Sample Article", liked = false), {})
}

