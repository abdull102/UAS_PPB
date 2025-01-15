package com.example.reeltime

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reeltime.data.LoginData
import com.example.reeltime.model.User
import com.example.reeltime.ui.theme.bgBlack
import com.example.reeltime.ui.theme.zinc400
import com.example.reeltime.ui.theme.zinc700

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    context: Context,
    onLoginSuccess: (String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val loginData = LoginData()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgBlack),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo1),
            contentDescription = stringResource(R.string.movie1)
        )
        Spacer(modifier.height(52.dp))

        // Username field
        Column(
            modifier = modifier
                .padding(start = 32.dp, end = 32.dp)
        ) {
            // sign in information
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "Enter your username and password here",
                style = MaterialTheme.typography.bodySmall,
                color = zinc400
            )
            Spacer(modifier.height(16.dp))
            // end sign in information

            // input username
            Text(
                text = "Username",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
            Spacer(modifier.height(4.dp))
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = zinc700,
                    unfocusedContainerColor = zinc700,
                    unfocusedBorderColor = zinc700,
                    focusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 12.sp),
                placeholder = {
                    Text(
                        text = "Enter your account username",
                        style = MaterialTheme.typography.labelLarge,
                        color = zinc400
                    )
                }
            )
            Spacer(modifier.height(16.dp))
            // end input username

            // input password
            Text(
                text = "Password",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
            Spacer(modifier.height(4.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = zinc700,
                    unfocusedContainerColor = zinc700,
                    unfocusedBorderColor = zinc700,
                    focusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(color = Color.White, fontSize = 12.sp),
                placeholder = {
                    Text(
                        text = "Enter your password",
                        style = MaterialTheme.typography.labelLarge,
                        color = zinc400
                    )
                }
            )
            // end input password
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Error message
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                // Simulate login logic
                if (username == "Marshanda" && password == "sasa") {
                    // Save login data to SharedPreferences
                    val user = User(username, password)
                    loginData.setLoginData(context, user)
                    onLoginSuccess(username)
                } else {
                    errorMessage = "Invalid username or password"
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Red
            )
        ) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White
            )
        }
    }
}