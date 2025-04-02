package com.example.wishlistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button

import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch


@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navHostController: NavHostController
) {
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        topBar = { AppBarView(
            title = if(id!=0L) stringResource(id = R.string.edit_item) else stringResource(id = R.string.add_item),
        ) {
            navHostController.navigateUp()
        }},
        scaffoldState = scaffoldState,
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
                        if (id != 0L) {
                            //update wish item
                            viewModel.updateWish(wish = Wish(id= id, title = viewModel.wishTitleState.trim(), description = viewModel.wishDescriptionState.trim()))
                            snackMessage.value = "Wish has been updated"
                        } else {
                            //Add Wish Item
                            viewModel.addWish(wish = Wish(title = viewModel.wishTitleState.trim(), description = viewModel.wishDescriptionState.trim()))
                            snackMessage.value = "Wish has been created"
                        }


                    } else {
                        snackMessage.value = "Enter fields to create a wish"
                    }
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)

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