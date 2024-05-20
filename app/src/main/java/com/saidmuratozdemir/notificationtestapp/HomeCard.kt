package com.saidmuratozdemir.notificationtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saidmuratozdemir.notificationtestapp.ui.theme.poppinsBlack
import com.saidmuratozdemir.notificationtestapp.ui.theme.poppinsMedium
import com.saidmuratozdemir.notificationtestapp.ui.theme.primaryTextColor

@Composable
fun HomeCard(
    title: String,
    subtitle: String,
    image: Int,
    isSwitchEnabled: Boolean = false,
    onClick: () -> Unit = {}
) {
    var checked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
        Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .size(width = 360.dp, height = 70.dp)
                .clip(RoundedCornerShape(50.dp))
                .border(width = 3.dp, color = Color.Transparent, shape = CircleShape)
                .background(primaryTextColor),

            Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .size(76.dp, height = 100.dp)
                    .padding(start = 8.dp)
                    .padding(end = 8.dp)
                    .fillMaxHeight(1f)
                    .clip(RoundedCornerShape(0.3f)),
                Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "HomeCardImage",
                    modifier = Modifier.size(60.dp)
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.fillMaxSize(), Arrangement.Center) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = poppinsBlack,
                        fontSize = 14.sp
                    )

                    Text(
                        text = subtitle,
                        modifier = Modifier.width(200.dp),
                        maxLines = 3,
                        fontFamily = poppinsMedium,
                        color = Color.Gray,
                        fontSize = 10.sp,
                    )
                }
                Column {
                    if (isSwitchEnabled) {
                        Switch(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            modifier = Modifier
                                .size(40.dp)
                                .padding(top = 30.dp)
                                .padding(start = 240.dp)
                        )
                    }
                }
            }
        }
    }
}