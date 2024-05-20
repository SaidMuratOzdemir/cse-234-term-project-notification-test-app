package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth


        super.onCreate(savedInstanceState)
        setContent {
            NotificationTestAppTheme {
                var showRegistrationScreen by remember { mutableStateOf(true) }

                if (showRegistrationScreen) {
                    RegistrationScreen {
                        showRegistrationScreen = false
                    }
                } else {
                    LoginActivity()
                }
            }
        }
    }

    @Composable
    fun RegistrationScreen(onRegistrationSuccess: () -> Unit) {
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier.size(190.dp)
                )
                Spacer(modifier = Modifier.height(150.dp))
                OutlinedTextField(value = email,
                    onValueChange = { email = it },
                    label = { Text("E-Mail") })
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    scope.launch {
                        val result = createUserWithEmailAndPassword(email, password)
                        if (result.isFailure) {
                            error = true
                            val errorMessage = when (val exception = result.exceptionOrNull()) {
                                is FirebaseAuthWeakPasswordException -> "Şifre en az 6 karakter olmalıdır."
                                is FirebaseAuthInvalidCredentialsException -> "Geçersiz e-posta adresi."
                                is FirebaseAuthUserCollisionException -> "Bu e-posta adresi zaten kullanılıyor."
                                else -> "Kayıt başarısız oldu: ${exception?.message}"
                            }
                            Toast.makeText(this@RegisterActivity, errorMessage, Toast.LENGTH_LONG)
                                .show()

                        } else {
                            Toast.makeText(this@RegisterActivity, "Başarılı", Toast.LENGTH_LONG)
                                .show()
                            finish()
                            return@launch
                        }
                    }

                }) {
                    Text("Kayıt Ol")
                }
            }
        }
    }
}


suspend fun createUserWithEmailAndPassword(email: String, password: String): Result<Unit> {
    return try {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).await()
        Result.success(Unit)
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}

