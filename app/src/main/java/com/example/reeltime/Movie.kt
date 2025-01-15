package com.example.reeltime

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.yellow
import com.example.reeltime.ui.theme.zinc400


@Composable
fun MoviePage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var player by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(context) {
        player = ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://com.example.reeltime/raw/moana")
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }

        onDispose {
            player?.release()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = bgBlack)
    ) {
        AndroidView(factory = { context ->
            PlayerView(context).apply {
                useController = true
            }
        }, update = { playerView ->
            playerView.player = player
        }, modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
        )
        Column(
            modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Moana 2", style = MaterialTheme.typography.headlineLarge, color = white
            )
            Text(
                text = "Adventure", style = MaterialTheme.typography.headlineSmall, color = zinc400
            )
        }


        // informasi movie
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "2023",
                    style = MaterialTheme.typography.headlineMedium,
                    color = white,
                )
                Text(
                    text = "Release Date",
                    style = MaterialTheme.typography.bodyLarge,
                    color = zinc400
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "4.5", style = MaterialTheme.typography.headlineMedium, color = white
                )
                Text(
                    text = "Rating", style = MaterialTheme.typography.bodyLarge, color = zinc400
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "1h 23m", style = MaterialTheme.typography.headlineMedium, color = white
                )
                Text(
                    text = "Duration", style = MaterialTheme.typography.bodyLarge, color = zinc400
                )
            }
        }

        // deskripsi movie
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "A family of ducks decides to leave the safety of a New England pond for an adventurous trip to Jamaica. However, their well-laid plans quickly go awry when they get lost and wind up in New York City. The experience soon inspires them to expand their horizons, open themselves up to new friends, and accomplish more than they ever thought possible.",
                style = MaterialTheme.typography.bodySmall,
                color = zinc400
            )
        }

        // how the movie
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = "How the movie?",
                style = MaterialTheme.typography.headlineLarge,
                color = white
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Take your review about this movie",
                style = MaterialTheme.typography.bodyLarge,
                color = zinc400
            )

            // stars
            Spacer(Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = yellow,
                    modifier = modifier.size(42.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = yellow,
                    modifier = modifier.size(42.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = yellow,
                    modifier = modifier.size(42.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Search Icon",
                    tint = yellow,
                    modifier = modifier.size(42.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.staroutline),
                    contentDescription = stringResource(id = R.string.movie1),
                    modifier = modifier.size(36.dp)
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = "274 Reviews have given a rating",
                style = MaterialTheme.typography.labelLarge,
                color = zinc400
            )
        }
    }
}