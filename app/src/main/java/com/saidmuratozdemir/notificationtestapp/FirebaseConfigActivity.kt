package com.saidmuratozdemir.notificationtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityFirebaseConfigBinding
import com.saidmuratozdemir.notificationtestapp.model.FirebaseConfig


class FirebaseConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseConfigBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirebaseConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.titleSpin.text = "Firebase Configurations"
        binding.toolbar.titleDes.text = "Firebase Configuration Settings"

        getFromSharedPreferenceIfSavedPreviously()
        imageButton()
        saveButton()
    }

    private fun getFromSharedPreferenceIfSavedPreviously() {
        val sharedPreferences = getSharedPreferences("configPrefs", MODE_PRIVATE)
        val firebaseConfigString = sharedPreferences.getString("firebaseJsonConfig", null)

        if (firebaseConfigString != null) {
            val firebaseConfig = FirebaseConfig.fromString(firebaseConfigString)

            if (firebaseConfig!!.url.isNotEmpty()) {
                binding.editTextFirebaseUrl.setText(firebaseConfig.url)
            }

            if (firebaseConfig.projectId.isNotEmpty()) {
                binding.editTextFirebaseProjectId.setText(firebaseConfig.projectId)
            }

            if (firebaseConfig.email.isNotEmpty()) {
                binding.editTextFirebaseEmail.setText(firebaseConfig.email)
            }

            if (firebaseConfig.apiKey.isNotEmpty()) {
                binding.editTextFirebaseApiKey.setText(firebaseConfig.apiKey)
            }
        }
    }

    private fun saveButton() {
        binding.buttonSaveFirebaseConfig.setOnClickListener {

            val url = binding.editTextFirebaseUrl.text.toString().trim()
            val projectId = binding.editTextFirebaseProjectId.text.toString().trim()
            val email = binding.editTextFirebaseEmail.text.toString().trim()
            val apiKey = binding.editTextFirebaseApiKey.text.toString().trim()

            if (url.isEmpty() || projectId.isEmpty() || email.isEmpty() || apiKey.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val firebaseConfig = FirebaseConfig(url, projectId, email, apiKey)

            val sharedPreferences = getSharedPreferences("configPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val jsonString = Gson().toJson(firebaseConfig)
            editor.putString("firebaseJsonConfig", jsonString)
            editor.apply()

            Toast.makeText(this, "Firebase configuration saved successfully!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun imageButton() {
        //TODO: will be implemented later

    }
}