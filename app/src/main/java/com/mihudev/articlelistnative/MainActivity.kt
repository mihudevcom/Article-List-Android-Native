package com.mihudev.articlelistnative

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mihudev.articlelistnative.ui.ArticleDetailsScreen
import com.mihudev.articlelistnative.ui.ArticleListScreen
import com.mihudev.articlelistnative.ui.theme.ArticleListNativeTheme
import com.mihudev.articlelistnative.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArticleListNativeTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars)
                ) {
                    val navController = rememberNavController()
                    val mainViewModel: MainViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "articleList"
                    ) {
                        composable("articleList") {
                            ArticleListScreen(
                                articles = mainViewModel.articles.collectAsState().value,
                                onArticleClick = { articleId ->
                                    navController.navigate("articleDetails/$articleId")
                                },
                                onLikeToggle = { articleId ->
                                    mainViewModel.onLikeToggle(articleId)
                                },
                                viewModel = mainViewModel
                            )
                        }

                        composable(
                            "articleDetails/{articleId}",
                            arguments = listOf(navArgument("articleId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val articleId = backStackEntry.arguments?.getInt("articleId")
                            val article =
                                mainViewModel.articles.collectAsState().value.find { it.id == articleId }
                            ArticleDetailsScreen(article = article)
                        }
                    }
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