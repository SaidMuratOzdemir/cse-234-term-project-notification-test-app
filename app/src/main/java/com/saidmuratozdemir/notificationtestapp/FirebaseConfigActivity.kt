package com.saidmuratozdemir.notificationtestapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.dataClasses.FirebaseConfigurationObject
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class FirebaseConfigActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FirebaseConfigScreen()
        }
    }

    @Composable
    fun FirebaseConfigScreen() {
        val sharedPrefs = getSharedPreferences("notificationApp", MODE_PRIVATE)
        var firebaseConfig by remember { mutableStateOf(loadFirebaseConfig(sharedPrefs)) }

        NotificationTestAppTheme {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.backgroundblur),
                    contentDescription = "back",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StringTextField("Project URL", firebaseConfig.projectUrl) { projectUrl ->
                        firebaseConfig = firebaseConfig.copy(projectUrl = projectUrl)
                    }
                    StringTextField("Project ID", firebaseConfig.projectId) { projectId ->
                        firebaseConfig = firebaseConfig.copy(projectId = projectId)
                    }
                    StringTextField("Email Address", firebaseConfig.emailAddress) { emailAddress ->
                        firebaseConfig = firebaseConfig.copy(emailAddress = emailAddress)
                    }
                    StringTextField(
                        "API Key",
                        firebaseConfig.apiKey,
                        visualTransformation = PasswordVisualTransformation()
                    ) { apiKey ->
                        firebaseConfig = firebaseConfig.copy(apiKey = apiKey)
                    }


                }

                Toolbar("FIREBASE CONFIGURATIONS", "Firebase Configuration Settings")
                Image(painter = painterResource(id = R.drawable.save),
                    contentDescription = "save button",
                    modifier = Modifier
                        .size(50.dp)
                        .offset(340.dp, 32.dp)
                        .clickable {
                            saveButton(firebaseConfig, sharedPrefs)
                        })
            }
        }
    }


    @Composable
    fun StringTextField(
        text: String,
        initialValue: String,
        visualTransformation: androidx.compose.ui.text.input.VisualTransformation = androidx.compose.ui.text.input.VisualTransformation.None,
        onValueChange: (String) -> Unit // Lambda for updating the state
    ) {
        var textFieldValue by remember { mutableStateOf(TextFieldValue(initialValue)) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .align(Alignment.CenterVertically)
                    .weight(0.3f), // Adjust the weight for better layout
                maxLines = 1,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )

            TextField(value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onValueChange(it.text) // Update the state
                },
                label = { Text("Enter $text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f) // Adjust the weight for better layout
                    .clip(CircleShape),
                visualTransformation = visualTransformation
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }


    private fun saveButton(
        firebaseConfig: FirebaseConfigurationObject, sharedPrefs: SharedPreferences
    ) {
        if (firebaseConfig.projectUrl.isEmpty() || firebaseConfig.projectId.isEmpty() || firebaseConfig.emailAddress.isEmpty() || firebaseConfig.apiKey.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        val editor = sharedPrefs.edit()
        val jsonString = Gson().toJson(firebaseConfig)
        editor.putString("firebaseConfiguration", jsonString)
        editor.apply()
        Toast.makeText(this, "Firebase configuration saved successfully!", Toast.LENGTH_SHORT)
            .show()
    }

    private fun loadFirebaseConfig(sharedPrefs: SharedPreferences): FirebaseConfigurationObject {
        val jsonString = sharedPrefs.getString("firebaseConfiguration", null)
        return Gson().fromJson(jsonString, FirebaseConfigurationObject::class.java)
            ?: FirebaseConfigurationObject("", "", "", "")
    }
}
