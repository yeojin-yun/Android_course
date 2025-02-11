package com.example.counterviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.counterviewmodel.ui.theme.CounterViewModelTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterViewModelTheme {
                CounterApp()
//                Scaffold { padding ->
//
//
//                }
            }
        }
    }
}

@Composable
fun CounterApp(viewModel: CounterViewModel = viewModel()) {
    val count by viewModel::count
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "counter: ${viewModel.count}")
        Row {
            Button(onClick = {
                viewModel.increment()
            }) {
                Text(text = "increment")
            }
            Button(onClick = {
                viewModel.decrement()
            }) {
                Text(text = "decrement")
            }
        }
    }
}

