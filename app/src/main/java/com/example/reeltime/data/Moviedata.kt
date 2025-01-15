package com.example.reeltime.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.reeltime.R
import com.example.reeltime.model.Moviemodel

class Moviedata {
    private val movies = listOf(
        Moviemodel(
            R.string.balikan,
            R.drawable.balikan,
            "balikan",
            R.string.description_balikan,
            "Trending"
        ),
        Moviemodel(
            R.string.joker,
            R.drawable.joker,
            "joker",
            R.string.description_joker,
            "Trending"
        ),
        Moviemodel(
            R.string.despicable,
            R.drawable.despicable,
            "despicable",
            R.string.description_despicable,
            "Animation"
        ),
        Moviemodel(
            R.string.saving,
            R.drawable.saving,
            "saving spongebob",
            R.string.description_saving,
            "Animation"
        ),
        Moviemodel(
            R.string.garfield,
            R.drawable.garfield,
            "garfield",
            R.string.description_garfield,
            "Animation"

        ),
        Moviemodel(
            R.string.beekeeper,
            R.drawable.beekeeper,
            "beekeeper",
            R.string.description_beekeeper,
            "Trending"
        ),
        Moviemodel(
            R.string.fastx,
            R.drawable.fastx,
            "fast x",
            R.string.description_fastx,
            "Trending"
        ),
        Moviemodel(
            R.string.moana,
            R.drawable.moana,
            "moana",
            R.string.description_moana,
            "Animation"
        ),
        Moviemodel(
            R.string.intestellar,
            R.drawable.interstellar,
            "interstellar",
            R.string.description_intestellar,
            "Trending"
        ),
        Moviemodel(
            R.string.five,
            R.drawable.five,
            "five feet apart",
            R.string.description_five,
            "Movie"
        ),
        Moviemodel(
            R.string.mylovely,
            R.drawable.myovely,
            "my lovely angel",
            R.string.description_mylovely,
            "Series"
        ),
    )

    fun loadMovies(): List<Moviemodel> {
        return movies
    }

    fun toggleFavorite(movie: Moviemodel) {
        movie.isFavorite = !movie.isFavorite
    }

    @Composable
    fun searchMovies(query: String): List<Moviemodel> {
        return movies.filter {
            it.title.lowercase().contains(query.lowercase()) ||
                    LocalContext.current.getString(it.stringResourceId).lowercase()
                        .contains(query.lowercase())
        }
    }

}