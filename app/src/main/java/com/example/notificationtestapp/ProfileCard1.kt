package com.example.notificationtestapp

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileCard(name:String,profilePicture: Int,gitHubLink:String,linkedInLink:String) {
    Column(
        modifier = Modifier
            .size(310.dp, 610.dp)
            .background(Color(0xFFF8F9FA))
            .fillMaxSize(1f)
            .border(BorderStroke(color = Color(0xFFFDE8A), width = 3.dp))
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.extraSmall,
            ),

        Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(id = profilePicture),
            contentDescription = "logo",
            modifier = Modifier
                .size(170.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            modifier = Modifier.size(100.dp, 100.dp),
            textAlign = TextAlign.Center,
            fontSize = 21.sp,
            fontWeight = FontWeight.ExtraBold,
            maxLines = 3,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.github),
            contentDescription = "github",
            modifier = Modifier
                .size(210.dp, 100.dp)
                .clickable { }
        )
        Image(
            painter = painterResource(id = R.drawable.linkedin),
            contentDescription = "linkedin",
            modifier = Modifier
                .size(210.dp, 100.dp)
                .clickable { }
        )
    }
    Spacer(modifier = Modifier.height(10.dp))


}