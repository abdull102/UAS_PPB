package com.example.reeltime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.zinc400

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = bgBlack)
            .padding(16.dp),
    ) {
        item {
            // hero section
            Column(
                modifier = modifier
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .padding(vertical = 32.dp)
                        .fillMaxWidth(),

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.arcanehero),
                        contentDescription = stringResource(id = R.string.movie1),
                        modifier = modifier
                            .height(228.dp)
                            .width(160.dp)
                            .align(AbsoluteAlignment.CenterLeft)
                            .offset(x = (0).dp) // Offset ke kiri
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jokerhero),
                        contentDescription = stringResource(id = R.string.movie1),
                        modifier = modifier
                            .height(228.dp)
                            .width(160.dp)
                            .align(AbsoluteAlignment.CenterLeft)
                            .offset(x = (42).dp) // Offset ke kiri
                    )

                    // Image Moana (Tengah)
                    Image(
                        painter = painterResource(id = R.drawable.moanahero),
                        contentDescription = stringResource(id = R.string.movie1),
                        modifier = modifier
                            .height(260.dp)
                            .width(160.dp)
                            .align(Alignment.Center) // Posisikan Moana di tengah Box
                            .zIndex(1f)
                            .clickable {
                                navController.navigate("movie_page") // Navigasi ke MoviePage
                            },
                    )
                    // Image Marvel (Kanan)
                    Image(
                        painter = painterResource(id = R.drawable.marvelhero),
                        contentDescription = stringResource(id = R.string.movie1),
                        modifier = modifier
                            .height(228.dp)
                            .width(160.dp)
                            .align(AbsoluteAlignment.CenterRight)
                            .offset(x = (0).dp) // Offset ke kanan
                    )
                    Image(
                        painter = painterResource(id = R.drawable.insidehero),
                        contentDescription = stringResource(id = R.string.movie1),
                        modifier = modifier
                            .height(228.dp)
                            .width(160.dp)
                            .align(AbsoluteAlignment.CenterRight)
                            .offset(x = (-42).dp) // Offset ke kanan
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Moana",
                        style = MaterialTheme.typography.headlineLarge,
                        color = white
                    )
                    Text(
                        text = "Animation",
                        style = MaterialTheme.typography.labelMedium,
                        color = zinc400
                    )
                }
            }
            // end hero section

            // trending movies
            Column(
                modifier = modifier.padding(top = 32.dp)
            ) {
                // link see more
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Trending Now",
                        style = MaterialTheme.typography.labelMedium,
                        color = white

                    )
                    TextButton (
                        onClick = { navController.navigate("filter_page?filter=Trending") }
                    ) {
                        Text(
                            text = "See more",
                            style = MaterialTheme.typography.labelSmall,
                            color = zinc400
                        )
                    }
                }

                // daftar trending movie
                LazyRow(
                    modifier = modifier.fillMaxWidth()
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.balikan),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.joker),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.beekeeper),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.fastx),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                    }

                }
            }
            // end trending movies

            // animation movies
            Column(
                modifier = modifier.padding(top = 32.dp)
            ) {
                // link see more
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Animation",
                        style = MaterialTheme.typography.labelMedium,
                        color = white

                    )
                    TextButton (
                        onClick = { navController.navigate("filter_page?filter=Animation") }
                    ) {
                        Text(
                            text = "See more",
                            style = MaterialTheme.typography.labelSmall,
                            color = zinc400
                        )
                    }
                }

                // daftar movie
                LazyRow(
                    modifier = modifier.fillMaxWidth()
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.despicable),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.saving),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.garfield),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.moana),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.inside),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                    }

                }
            }
            // end animation movies

            // series
            Column(
                modifier = modifier.padding(top = 32.dp)
            ) {
                // link see more
                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Series", style = MaterialTheme.typography.labelMedium, color = white

                    )
                    TextButton (
                        onClick = { navController.navigate("filter_page?filter=Series") }
                    ) {
                        Text(
                            text = "See more",
                            style = MaterialTheme.typography.labelSmall,
                            color = zinc400
                        )
                    }
                }

                // daftar movie
                LazyRow(
                    modifier = modifier.fillMaxWidth()
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.whenphone),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.queen),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)

                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.arcane),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                        Spacer(modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.myovely),
                            contentDescription = stringResource(id = R.string.movie1),
                            modifier = modifier
                                .height(170.dp)
                                .width(120.dp)
                        )
                    }

                }
            }
            // end series
        }

    }

}