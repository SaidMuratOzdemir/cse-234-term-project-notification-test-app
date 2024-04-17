package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.toolbar.titleSpin.text = "About Us"
        binding.toolbar.titleDes.text = "My Social Media Accounts"

        binding.githubMurat.setOnClickListener {
            val githubUrl = "https://github.com/SaidMuratOzdemir"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            startActivity(intent)
        }

        binding.linkedinMurat.setOnClickListener {
            val linkedinUrl = "https://www.linkedin.com/in/s-murat-ozdemir/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl))
            startActivity(intent)
        }

        binding.githubRepo.setOnClickListener {
            val githubUrl = "https://github.com/SaidMuratOzdemir/Notification-Test-App"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            startActivity(intent)
        }
    }
}
