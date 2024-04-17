package com.saidmuratozdemir.notificationtestapp.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

data class FirebaseConfig(
    val url: String,
    val projectId: String,
    val email: String,
    val apiKey: String
) {
    companion object {
        fun fromString(jsonString: String): FirebaseConfig? {
            return try {
                Gson().fromJson(jsonString, FirebaseConfig::class.java)
            } catch (e: JsonSyntaxException) {
                // Handle JSON parsing errors gracefully
                Log.e("FirebaseConfig", "Error parsing JSON: $e")
                null
            }
        }
    }
}
