package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (checkIfUserIsLoggedIn()) {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
            return
        } else {
            setContent {
                MainScreen()

            }
        }
    }

    @Composable
    @Preview
    fun MainScreen() {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.backgroundblur),
                contentDescription = "Arka Plan Resmi",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(modifier =Modifier.size(300.dp,580.dp)){

                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "Arka Plan Resmi",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .shadow(
                            elevation = 1.dp,
                            shape = MaterialTheme.shapes.extraSmall,
                        )
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,


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
                            onClick = {
                                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            },
                            modifier = Modifier.size(120.dp, 60.dp)
                        ) { Text(text = "Sign In") }
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                        }, modifier = Modifier.size(120.dp, 60.dp)) {
                            Text(text = "Sign Up")
                        }
                    }
                }

            }

        }

    }

    override fun onStart() {
        super.onStart()
        if (checkIfUserIsLoggedIn()) {
            startActivity(Intent(this, HomeScreenActivity::class.java))
            finish()
        }
    }

    private fun checkIfUserIsLoggedIn(): Boolean {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        return firebaseUser != null
    }
}
