package com.example.reeltime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.reeltime.data.Moviedata
import com.example.reeltime.model.Moviemodel
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.red
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.zinc400
import com.example.reeltime.ui.theme.zinc700

@Composable
fun SearchPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    moviedata: Moviedata,
    favoriteMovies: MutableList<Moviemodel>
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = bgBlack)
            .padding(16.dp)
    ) {
        Text(
            text = "Search", style = MaterialTheme.typography.headlineMedium, color = white
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(99.dp))
                .background(color = zinc700), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = zinc400,
                modifier = modifier.padding(start = 12.dp, end = 2.dp)
            )

            OutlinedTextField(value = text,
                onValueChange = { text = it },
                modifier = modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(Color.Transparent),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = zinc700,
                    unfocusedContainerColor = zinc700,
                    unfocusedBorderColor = zinc700,
                    focusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 12.sp),
                placeholder = {
                    Text(
                        text = "Find Movies and Series",
                        style = MaterialTheme.typography.labelLarge,
                        color = zinc400
                    )
                }
            )
        }

        Spacer(Modifier.height(8.dp))

        // Tampilkan MovieList sesuai dengan pencarian atau tanpa pencarian
        val movieList = if (text.isEmpty()) {
            moviedata.loadMovies()
        } else {
            moviedata.searchMovies(text)
        }

        MovieList(
            movielist = movieList,
            navController = navController,
            moviedata = moviedata,
            favoriteMovies = favoriteMovies
        )
    }
}

@Composable
fun MovieList(
    movielist: List<Moviemodel>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    moviedata: Moviedata,
    favoriteMovies: MutableList<Moviemodel>
) {
    LazyColumn {
        items(movielist) { moviemodel ->
            MovieCard(moviemodel = moviemodel,
                navController = navController,
                onAddToFavorite = { movie ->
                    moviedata.toggleFavorite(movie)
                    if (!favoriteMovies.contains(movie)) {
                        favoriteMovies.add(movie) // Menambahkan film ke wishlist jika belum ada
                    } else {
                        favoriteMovies.remove(movie) // Menghapus film dari wishlist jika sudah ada
                    }
                })
            Spacer(modifier.height(8.dp))
        }
    }
}


@Composable
fun MovieCard(
    moviemodel: Moviemodel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onAddToFavorite: (Moviemodel) -> Unit // Callback untuk menambah atau menghapus favorit
) {
    OutlinedCard(
        modifier = modifier.padding(0.dp), colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ), border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(moviemodel.imageResourceId),
                contentDescription = stringResource(moviemodel.stringResourceId),
                modifier = modifier
                    .size(height = 170.dp, width = 120.dp)
                    .padding(end = 12.dp)
            )
            Column(
                modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = LocalContext.current.getString(moviemodel.stringResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    color = white
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = LocalContext.current.getString(moviemodel.descriptionId),
                    style = MaterialTheme.typography.bodySmall,
                    color = zinc400
                )

                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { navController.navigate("movie_page") },
                        colors = ButtonDefaults.buttonColors(containerColor = red),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.play),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .size(12.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Play now",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Button(
                        onClick = { onAddToFavorite(moviemodel) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (moviemodel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorite",
                                tint = if (moviemodel.isFavorite) Color.Red else Color.Gray
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = "Add to wishlist",
                                style = MaterialTheme.typography.bodySmall,
                                color = zinc400
                            )
                        }

                    }
                }

            }
        }
    }
}
