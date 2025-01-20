package com.mihudev.articlelistnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                    ArticleList(
                        articles = listOf(
                            Article(1, "Article 1", false),
                            Article(2, "Article 2", true),
                            Article(3, "Article 3", false)
                        )
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