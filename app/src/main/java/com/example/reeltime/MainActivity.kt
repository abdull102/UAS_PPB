package com.example.reeltime

import FilterPage
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.reeltime.data.LoginData
import com.example.reeltime.data.Moviedata
import com.example.reeltime.model.Moviemodel
import com.example.reeltime.model.User
import com.example.reeltime.ui.theme.ReeltimeTheme
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.red
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.yellow
import com.example.reeltime.ui.theme.zinc400
import com.example.reeltime.ui.theme.zinc700
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReeltimeTheme {
                ReeltimeApp()
            }
        }
    }
}


@Composable
fun ReeltimeApp() {
    val showLandingScreen = remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val movieData = remember { Moviedata() }
    val favoriteMovies = remember { mutableStateListOf<Moviemodel>() }
    val isLoggedIn = remember { mutableStateOf(false) }
    var loggedInUser by remember { mutableStateOf("") }


    LaunchedEffect(key1 = true) {
        delay(2000)
        showLandingScreen.value =
            false
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(bottomBar = {
        if (!showLandingScreen.value && currentRoute != "login_page") {
            Navbar(navController = navController)
        }
    }) { innerPadding ->
        if (showLandingScreen.value) {
            LandingScreen()
        } else {
            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn.value) "home_page" else "login_page",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("login_page") {
                    LoginScreen(
                        context = LocalContext.current,
                        onLoginSuccess = { username ->
                            isLoggedIn.value = true
                            loggedInUser = username
                            navController.navigate("home_page")
                        }
                    )
                }
                composable("home_page") {
                    HomeScreen(navController = navController)
                }
                composable("movie_page") {
                    MoviePage()
                }
                composable("search_page") {
                    SearchPage(
                        navController = navController,
                        moviedata = movieData,
                        favoriteMovies = favoriteMovies
                    )
                }
                composable("wishlist_page") {
                    WishlistPage(navController = navController, favoriteMovies = favoriteMovies)
                }
                composable("profil_page") {
                    ProfilPage(navController = navController, username = loggedInUser)
                }
                composable(
                    route = "filter_page?filter={filter}",
                    arguments = listOf(navArgument("filter") { type = NavType.StringType })
                ) { navBackStackEntry ->
                    FilterPage(navController, navBackStackEntry)
                }
            }
        }
    }

//    HomeScreen(navController = navController)

}

@Composable
fun LandingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = bgBlack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = stringResource(id = R.string.movie1),
        )
    }
}

@Composable
fun Navbar(navController: NavHostController, modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = bgBlack, contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = bgBlack)
                .padding(start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // search
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Outlined.Home,
                    contentDescription = "Search Icon",
                    tint = white,
                    modifier = modifier.clickable {
                        navController.navigate("home_page")
                    })
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Home", style = MaterialTheme.typography.labelSmall, color = white
                )
            }
            // search
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = white,
                    modifier = modifier.clickable {
                        navController.navigate("search_page")
                    })
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Search", style = MaterialTheme.typography.labelSmall, color = white
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Search Icon",
                    tint = white,
                    modifier = modifier.clickable {
                        navController.navigate("wishlist_page")
                    })
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Wishlist", style = MaterialTheme.typography.labelSmall, color = white
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Search Icon",
                    tint = white,
                    modifier = modifier.clickable {
                        navController.navigate("profil_page")
                    })
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Account", style = MaterialTheme.typography.labelSmall, color = white
                )
            }
        }
    }
}

@Preview
@Composable
private fun Coba() {
    ReeltimeTheme {
        ReeltimeApp()
    }
}