package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = { AppBarView(
            title = if(id!=0L) stringResource(id = R.string.edit_item) else stringResource(id = R.string.update_item),
        ) {
            navHostController.navigateUp()
        }}
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title", content = viewModel.wishTitleState) { newTitle ->
                viewModel.wishTitleChanged(newTitle)
            }
            WishTextField(label = "Description", content = viewModel.wishDescriptionState) { newDescription ->
                viewModel.wishDescriptionChanged(newDescription)
            }
            Button(
                onClick = {
                    if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {
                        //Update Wish Item
                    } else {
                        //Add Wish Item
                    }
                }
            ) {
                Text(
                    text = if(id == 0L) stringResource(id = R.string.add) else stringResource(id = R.string.edit),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    content: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = content,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        label = { Text(text = label)},
        onValueChange = onValueChanged,
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.grey),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.grey),
        )
    )
}