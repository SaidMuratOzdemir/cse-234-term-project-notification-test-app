package com.example.notificationtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun History1() {

    var boxVisible by remember { mutableStateOf(true) }
    Toolbar1("HISTORY","All the notifications has sent your device")
    Image(
        painter = painterResource(id = R.drawable.delete2),
        contentDescription = "delete",
        modifier = Modifier
            .size(50.dp)
            .offset(320.dp, 12.dp)
            .clickable(onClick = { boxVisible = false }),

        )
        var i=1
    if (boxVisible) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 110.dp)

        ) {
            items(10) {
            HomeCard1(
                title = "Notification "+i+" Title",
                subtitle = "Notification "+i+++" Description",
                R.drawable.ic_launcher_foreground,false
            )
                    Spacer(modifier = Modifier.height(10.dp))
            }
        }


    }
}






