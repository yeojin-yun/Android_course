package com.example.musicapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubscriptionView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Manage Subscription")
        Card(
            modifier = Modifier.padding(0.dp),
            
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Musical")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Free Tier")
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "See All Plans")
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "See All Plans")
                    }
                }
            }
            Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
            Row(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Get a Plan")
                Text(text = "Get a Plan")
            }
        }
    }
}