package com.mihudev.articlelistnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mihudev.articlelistnative.model.Article
import com.mihudev.articlelistnative.ui.ArticleList
import com.mihudev.articlelistnative.ui.theme.ArticleListNativeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleListNativeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    var articles by remember {
                        mutableStateOf(
                            listOf(
                                Article(1, "Article 1", false),
                                Article(2, "Article 2", true),
                                Article(3, "Article 3", false)
                            )
                        )
                    }

                    ArticleList(
                        articles = articles,
                        onLikeToggle = { articleId ->
                            articles = articles.map { article ->
                                if (article.id == articleId) article.copy(liked = !article.liked)
                                else article
                            }
                        }
                    )

                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArticleListNativeTheme {
        Greeting("Android")
    }
}