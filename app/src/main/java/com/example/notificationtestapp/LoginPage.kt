package com.example.notificationtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun logInPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier.size(190.dp)
        )
        Spacer(modifier = Modifier.height(150.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(120.dp, 60.dp)
            ) { Text(text = "Log In") }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.size(120.dp, 60.dp)) {
                Text(text = "Register")
            }
        }

    }

}


