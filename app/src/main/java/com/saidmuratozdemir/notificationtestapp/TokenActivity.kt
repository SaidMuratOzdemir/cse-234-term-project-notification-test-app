package com.saidmuratozdemir.notificationtestapp

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityTokenBinding
import com.saidmuratozdemir.notificationtestapp.model.FirebaseConfig

class TokenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTokenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.titleSpin.text = "Token"
        binding.toolbar.titleDes.text = "Your Firebase Token. That is unique for your device."

        val token = intent.getStringExtra("token")
        binding.textviewToken.text = token

        val sharedPreferences = getSharedPreferences("configPrefs", MODE_PRIVATE)
        val firebaseConfigString = sharedPreferences.getString("firebaseJsonConfig", null)

        val email = if (firebaseConfigString != null) {
            FirebaseConfig.fromString(firebaseConfigString)?.email
        } else {
            null
        }

        if (email != null) {
            binding.textViewEmail.text = email
        }

        copyButton()
        shareButton()

    }

    private fun copyButton() {
        binding.copyButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(
                android.content.ClipData.newPlainText(
                    "token",
                    binding.textviewToken.text
                )
            )
            Snackbar.make(
                binding.root,
                "Token copied to clipboard",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    private fun shareButton() {
        binding.shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, binding.textviewToken.text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}