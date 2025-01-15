package com.example.reeltime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.reeltime.model.Moviemodel
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.zinc400

@Composable
fun WishlistPage(
    modifier: Modifier = Modifier, navController: NavController, favoriteMovies: List<Moviemodel>
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgBlack)
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Wishlist",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "Watch your wishlist movie",
                style = MaterialTheme.typography.bodySmall,
                color = zinc400
            )
        }

        Spacer(Modifier.height(24.dp))

        LazyColumn {
            items(favoriteMovies) { movie ->
                MovieCard(moviemodel = movie,
                    navController = navController as NavHostController,
                    onAddToFavorite = {})
                Spacer(Modifier.height(8.dp))
            }
        }

    }
}
