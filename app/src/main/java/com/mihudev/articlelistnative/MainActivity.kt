package com.mihudev.articlelistnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
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

                    val mainViewModel:MainViewModel = viewModel()

                    val articles = mainViewModel.articles.collectAsState().value

                    ArticleList(
                        articles = articles,
                        onLikeToggle = { articleId -> mainViewModel.onLikeToggle(articleId)
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