package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var error by remember { mutableStateOf(false) }
                val scope = rememberCoroutineScope()

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.backgroundblur),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop

                    )
                    Box(modifier = Modifier.size(300.dp, 580.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "Arka Plan Resmi",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )


                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "App logo",
                                modifier = Modifier.size(190.dp)
                            )
                            Spacer(modifier = Modifier.height(150.dp))

                            OutlinedTextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("E-Mail") },
                                modifier = Modifier.shadow(
                                    elevation = 1.dp, shape = RoundedCornerShape(8.dp)
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    cursorColor = Color.Black
                                )

                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text("Password") },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier.shadow(
                                    elevation = 1.dp, shape = RoundedCornerShape(8.dp)
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    cursorColor = Color.Black
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    scope.launch { // Launch coroutine for async work
                                        val result = signInWithEmailAndPassword(email, password)
                                        if (result.isFailure) {
                                            error = true
                                            val errorMessage =
                                                when (val exception = result.exceptionOrNull()) {
                                                    is FirebaseAuthInvalidUserException -> "Bu e-posta adresiyle kayıtlı bir kullanıcı yok."
                                                    is FirebaseAuthInvalidCredentialsException -> "E-posta veya şifre yanlış."
                                                    else -> "Giriş başarısız oldu. Lütfen tekrar deneyin."  // Genel hata mesajı
                                                }
                                            Toast.makeText(
                                                this@LoginActivity, errorMessage, Toast.LENGTH_LONG
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                this@LoginActivity, "Başarılı", Toast.LENGTH_LONG
                                            ).show()
                                            finish()
                                            return@launch
                                        }
                                    }

                                }, colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF382c84)
                                )
                            ) {
                                Text("Giriş Yap")
                            }
                        }


                    }

                }


            }
        }
    }
}

suspend fun signInWithEmailAndPassword(email: String, password: String): Result<Unit> {
    return try {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
