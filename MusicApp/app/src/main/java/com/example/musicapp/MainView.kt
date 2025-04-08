package com.example.musicapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.Screen.BottomScreen.Browse.screensInBottom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainView() {
    //viewModel
    val viewModel: MainViewModel = viewModel()

    //scaffold state
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    //Allow us to find out on which "View" we current are
    val controller: NavHostController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //current Screen
    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    //set drawer screen title
    val title = remember {
        mutableStateOf(currentScreen.title)
    }

    val dialogOpen = remember {
        mutableStateOf(false)
    }

    val bottomBar: @Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(
                Modifier.wrapContentSize()
            ) {
                screensInBottom.forEach { item -> 
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = { controller.navigate(item.bRoute)},
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription =  item.bTitle) },
                        label = { Text(text = item.bTitle) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )

                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title.value)/*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "nav icon")
                    }
                }

            )
        },
        drawerContent = {
            LazyColumn {
                items(screensInDrawer) { screen ->
                    DrawerItem(selected = currentRoute == screen.dRoute, item = screen, onDrawerItemClicked = {
                        scope.launch {
                            //drawer 중에 하나 선택하면 drawer이 닫히도록
                            scaffoldState.drawerState.close()
                        }
                        if (screen.dRoute == "add_account") {
                            dialogOpen.value = true
                        } else {
                            controller.navigate(screen.dRoute)
                            title.value = screen.dTitle
                        }
                    })
                }
            }
        },
        bottomBar = {

        },
        scaffoldState = scaffoldState
    ) {
        Navigation(navController = controller, viewModel= viewModel,paddingValue = it)
        AccountDialog(dialogOpen = dialogOpen)
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    onDrawerItemClicked: () -> Unit,
    item: Screen.DrawerScreen,
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(color = background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(painter = painterResource(id = item.icon), contentDescription = item.dTitle)
        Text(text = item.dTitle, style = MaterialTheme.typography.bodyLarge)
    }
}