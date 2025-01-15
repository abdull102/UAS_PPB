package com.example.reeltime

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.red
import com.example.reeltime.ui.theme.white
import com.example.reeltime.ui.theme.zinc400

@Composable
fun ProfilPage(modifier: Modifier = Modifier, navController: NavController, username: String) {
    Column(
        modifier = modifier
            .background(bgBlack)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // informasi akun
        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Account",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Spacer(modifier.height(48.dp))
            Image(
                painter = painterResource(R.drawable.profil),
                contentDescription = stringResource(R.string.movie1),
                modifier = modifier
                    .size(150.dp)
            )
            Spacer(modifier.height(12.dp))
            Text(
                text = username,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "@marshanda_a",
                style = MaterialTheme.typography.bodyLarge,
                color = zinc400
            )

            // informasi akun
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
                        text = "2024",
                        style = MaterialTheme.typography.headlineMedium,
                        color = white,
                    )
                    Text(
                        text = "Join Since",
                        style = MaterialTheme.typography.bodyLarge,
                        color = zinc400
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "23", style = MaterialTheme.typography.headlineMedium, color = white
                    )
                    Text(
                        text = "Total Movies",
                        style = MaterialTheme.typography.bodyLarge,
                        color = zinc400
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "42h 23m",
                        style = MaterialTheme.typography.headlineMedium,
                        color = white
                    )
                    Text(
                        text = "Watch Time",
                        style = MaterialTheme.typography.bodyLarge,
                        color = zinc400
                    )
                }
            }
            // end informasi akun

            // detail akun
            Column(
                modifier = modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth()
            ) {
                // fullname
                Text(
                    text = "Fullname",
                    style = MaterialTheme.typography.bodyLarge,
                    color = zinc400
                )
                Text(
                    text = "Marshanda",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Spacer(modifier.height(8.dp))
                // email
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.bodyLarge,
                    color = zinc400
                )
                Text(
                    text = "sasa@gmail.com",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Spacer(modifier.height(8.dp))
                // username
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.bodyLarge,
                    color = zinc400
                )
                Text(
                    text = "@marshanda_a",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
        // end informasi akun

        // logout
        Button(
            onClick = {
                navController.navigate("login_page")
            },
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = red
            )
        ) {
            Image(
                painter = painterResource(R.drawable.logout),
                contentDescription = stringResource(R.string.movie1),
                modifier = modifier
                    .size(12.dp)
            )
            Spacer(modifier.width(4.dp))
            Text(
                text = "Sign Out",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
        }
    }
}
