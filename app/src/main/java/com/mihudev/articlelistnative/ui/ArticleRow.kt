package com.mihudev.articlelistnative.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mihudev.articlelistnative.model.Article


@Composable
fun ArticleRow(
    article: Article,
    onLikeToggle: (Int) -> Unit,
    onArticleClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onArticleClick(article.id) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val scale by animateFloatAsState(
            targetValue = if (article.liked) 1.2f else 1.0f
        )


        Text(text = article.title)
        IconButton(
            onClick = { onLikeToggle(article.id) },
            modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
        ) {
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
    ArticleRow(Article(1, "Sample Article", liked = false), {}, {})
}

