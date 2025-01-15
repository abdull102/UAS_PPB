import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.reeltime.R
import com.example.reeltime.data.Moviedata
import com.example.reeltime.model.Moviemodel
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.red
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.zinc400

@Composable
fun FilterPage(navController: NavHostController,  navBackStackEntry: NavBackStackEntry?, modifier: Modifier = Modifier) {
    val filter = navBackStackEntry?.arguments?.getString("filter") ?: "movie"
    val moviedata = Moviedata()
    val filteredMovies = moviedata.loadMovies().filter { it.filter == filter }

    Column(
        modifier = modifier
            .background(bgBlack)
            .padding(16.dp)
    ) {
        // heading
        Text(
            text = filter,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        Spacer(modifier.height(4.dp))
        if (filter == "Trending"){
            Text(
                text = "See the most trending movies",
                style = MaterialTheme.typography.bodyMedium,
                color = zinc400
            )
        } else if (filter == "Animation"){
            Text(
                text = "Enjoy the best animation movies",
                style = MaterialTheme.typography.bodyMedium,
                color = zinc400
            )
        } else if (filter == "Series"){
            Text(
                text = "Watch popular series and drama",
                style = MaterialTheme.typography.bodyMedium,
                color = zinc400
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // end heading

        FilterList(
            movielist = filteredMovies,
            moviedata = moviedata,
            navController = navController,
            favoriteMovies = remember { mutableStateListOf() }
        )
    }
}

@Composable
fun FilterList(
    navController: NavController,
    movielist: List<Moviemodel>,
    modifier: Modifier = Modifier,
    moviedata: Moviedata,
    favoriteMovies: MutableList<Moviemodel>
) {
    LazyColumn(modifier = modifier) {
        items(items = movielist, key = {movie -> movie.stringResourceId}) { moviemodel ->
            FilterCard(
                moviemodel = moviemodel,
                navController = navController,
                onAddToFavorite = { movie ->
                    moviedata.toggleFavorite(movie)
                    if (!favoriteMovies.contains(movie)) {
                        favoriteMovies.add(movie)
                    } else {
                        favoriteMovies.remove(movie)
                    }
                }
            )
            Spacer(modifier.height(8.dp))
        }
    }
}

@Composable
fun FilterCard(
    moviemodel: Moviemodel,
    modifier: Modifier = Modifier,
    onAddToFavorite: (Moviemodel) -> Unit,
    navController: NavController
) {
    OutlinedCard(
        modifier = modifier.padding(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Gambar film
            Image(
                painter = painterResource(moviemodel.imageResourceId),
                contentDescription = stringResource(moviemodel.stringResourceId),
                modifier = modifier
                    .size(height = 170.dp, width = 120.dp)
                    .padding(end = 12.dp)
            )
            // Kolom untuk judul, deskripsi, dan tombol aksi
            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Judul film
                Text(
                    text = LocalContext.current.getString(moviemodel.stringResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    color = white
                )
                Spacer(Modifier.height(4.dp))
                // Deskripsi film
                Text(
                    text = LocalContext.current.getString(moviemodel.descriptionId),
                    style = MaterialTheme.typography.bodySmall,
                    color = zinc400,
                    maxLines = 3 // Batasi jumlah baris deskripsi
                )

                Spacer(Modifier.height(24.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Tombol Play
                    Button(
                        onClick = { navController.navigate("movie_page") },
                        colors = ButtonDefaults.buttonColors(containerColor = red),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.play), // Pastikan import R.drawable.play benar
                            contentDescription = "Play",
                            modifier = modifier.size(12.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = "Play now",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    // Tombol Add to Wishlist
                    IconButton(onClick = { onAddToFavorite(moviemodel) }) {
                        Icon(
                            imageVector = if (moviemodel.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (moviemodel.isFavorite) Color.Red else Color.Gray
                        )
                    }
                }
            }
        }
    }
}