package com.example.notificationtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max

@Composable
@Preview
fun SeeToken() {

    Toolbar("TOKEN","Your Firebase Token. That is unique for your device.")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 300.dp)
            .size(300.dp)
    ) {
        Text(
            text = "qMPOy2WhCedrQrLUQoMtP1xZmGtW92LJJigVj1TdBb7C4j8AVxxmwXZgAvBF4YZPiVPzUvVjawbe2TYzZBzO0LV1d9PSgCkVDXPCkQRcDaJ",
            modifier = Modifier
                .fillMaxWidth()
                .size(130.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,

            )
        Row(modifier = Modifier.fillMaxSize(), Arrangement.Absolute.SpaceEvenly) {
            Button(
                onClick = { }, modifier = Modifier
                    .size(105.dp, 40.dp)
                    .clip(CircleShape),



            ) {
                Row(modifier = Modifier.size(100.dp),){
                    Image(
                        painter = painterResource(id = R.drawable.copy),
                        contentDescription = "copy button",
                        modifier = Modifier

                    )
                    Text(text = "Copy", modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,


                        )
                }


            }

            Button(
                onClick = { }, modifier = Modifier
                    .size(105.dp, 40.dp)
                    .clip(CircleShape)
                    .clickable {  },



                ) {
                Row(modifier = Modifier.size(100.dp),){
                    Image(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "share button",
                        modifier = Modifier

                    )
                    Text(text = "Share", modifier = Modifier.fillMaxWidth().padding(top = 3.dp),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        fontSize = 12.sp



                        )
                }


            }
        }

    }

}