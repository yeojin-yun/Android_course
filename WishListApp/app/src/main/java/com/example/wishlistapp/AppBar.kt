package com.example.wishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    //status bar color까지 통일 시키기
    val systemUiController = rememberSystemUiController()
    val pinkColor = colorResource(id = R.color.app_bar_color)
    SideEffect {
        systemUiController.setStatusBarColor(
            color = pinkColor,

        )
    }

    //nav back icon
    val navIcon: @Composable (() -> Unit)? = {
        if (!title.contains("Wish")) {
            IconButton(onClick = onBackNavClicked) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription ="arrow_back_icon")
            }
        } else {
            null
        }
    }

    TopAppBar(
        modifier = Modifier.statusBarsPadding(), //statusbar에 겹치지 않도록 앱바에 패딩 주기
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp),
                
            )
        },
        elevation = 3.dp,
        backgroundColor = pinkColor,
        navigationIcon = navIcon
    )
}