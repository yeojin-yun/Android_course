package com.example.musicapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>) {
    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = { 
            dialogOpen.value = false
            },
            confirmButton = {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Confirm")
                }
            },
            title = {
                Text(text = "Add Account")
            },
            text = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(16.dp)
                ) {
                    TextField(value = "ID", label = { Text(text = "Email")}, modifier = Modifier.padding(16.dp), onValueChange = {})
                    TextField(value = "PW", label = { Text(text = "Password")}, modifier = Modifier.padding(16.dp),onValueChange = {})
                }
            },
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primarySurface).padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            containerColor = Color.White,
            properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            )
        )
    }
}