package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
                            onClick = {
                                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            },
                            modifier = Modifier.size(120.dp, 60.dp)
                        ) { Text(text = "Log In") }
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                        }, modifier = Modifier.size(120.dp, 60.dp)) {
                            Text(text = "Register")
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