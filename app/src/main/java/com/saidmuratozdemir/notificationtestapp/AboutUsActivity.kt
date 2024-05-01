package com.saidmuratozdemir.notificationtestapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saidmuratozdemir.notificationtestapp.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AboutUsScreen(
                onClickGithub = {
                    val githubUrl = "https://github.com/SaidMuratOzdemir"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                    startActivity(intent)
                },
                onClickLinkedin = {
                    val linkedinUrl = "https://www.linkedin.com/in/s-murat-ozdemir/"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinUrl))
                    startActivity(intent)
                },
                onClickRepo = {
                    val githubUrl = "https://github.com/SaidMuratOzdemir/Notification-Test-App"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
                    startActivity(intent)
                }
            )
        }
    }

    @Composable
    fun AboutUsScreen(onClickGithub: () -> Unit, onClickLinkedin: () -> Unit, onClickRepo: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            Toolbar(title = "About Us", subtitle = "My Social Media Accounts")
            ProfileImage(imageResource = R.drawable.murat)
            ProfileName(name = "Said Murat\nÖZDEMİR")
            SocialMediaButton(imageResource = R.drawable.github, onClick = onClickGithub)
            SocialMediaButton(imageResource = R.drawable.linkedin, onClick = onClickLinkedin)
            Text(text = "Github Repository of the Project", fontSize = 16.sp, color = Color.Red)
            SocialMediaButton(imageResource = R.drawable.github, onClick = onClickRepo)
        }
    }

    @Composable
    fun Toolbar(title: String, subtitle: String) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
            )
            Column {
                Text(
                    text = title,
                    style = TextStyle(color = Color.Black, fontSize = 24.sp),
//                    style = MaterialTheme.typography.h5,
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
                )
                Text(
                    text = subtitle,
                    style = TextStyle(color = Color.Black, fontSize = 16.sp),
                    color = Color.Blue,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                )
            }
        }
    }

    @Composable
    fun ProfileImage(imageResource: Int) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(180.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }

    @Composable
    fun ProfileName(name: String) {
        Text(
            text = name,
            style = TextStyle(color = Color.Black, fontSize = 24.sp),
            color = Color.Yellow,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 12.dp)
        )
    }

    @Composable
    fun SocialMediaButton(imageResource: Int, onClick: () -> Unit) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(180.dp)
                .padding(top = 16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Social Media Button"
            )
        }
    }
}