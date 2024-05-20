package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("E-Mail") })
                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        scope.launch { // Launch coroutine for async work
                            val result = signInWithEmailAndPassword(email, password)
                            if (result.isFailure) {
                                error = true
                                val errorMessage = when (val exception = result.exceptionOrNull()) {
                                    is FirebaseAuthInvalidUserException -> "Bu e-posta adresiyle kayıtlı bir kullanıcı yok."
                                    is FirebaseAuthInvalidCredentialsException -> "E-posta veya şifre yanlış."
                                    else -> "Giriş başarısız oldu. Lütfen tekrar deneyin."  // Genel hata mesajı
                                }
                                Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this@LoginActivity, "Başarılı", Toast.LENGTH_LONG).show()
                                finish()
                                return@launch
                            }
                        }

                    }) {
                        Text("Giriş Yap")
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
