package com.example.musicapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
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

    val isSheetFullScreen by remember {
        mutableStateOf(false)
    }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
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

    val modalBottomSheetState = androidx.compose.material.rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden) {
        it != ModalBottomSheetValue.HalfExpanded
    }

    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

    val bottomBar: @Composable () -> Unit = {
        if(currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(
                Modifier
                    .wrapContentSize()
                    .navigationBarsPadding()
            ) {
                screensInBottom.forEach { item ->
                    val isSelected:Boolean = currentRoute == item.bRoute;
                    val tint = if (isSelected) Color.White else Color.Black
                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle },
                        icon = { Icon(painter = painterResource(id = item.icon), contentDescription =  item.bTitle, tint = tint) },
                        label = { Text(text = item.bTitle) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )

                }
            }
        }
    }

    androidx.compose.material.ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        ModalBottomSheetLayout(modifier = modifier)
    }) {
        Scaffold(
            bottomBar = bottomBar,
            topBar = {
                TopAppBar(
                    title = { Text(text = title.value)/*TODO*/ },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if (modalBottomSheetState.isVisible)
                                    modalBottomSheetState.hide()
                                else
                                    modalBottomSheetState.show()
                            }
                        }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more button")
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "nav icon")
                        }
                    },
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                )
            },
            drawerContent = {
                LazyColumn(
                    modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
                ) {
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

            scaffoldState = scaffoldState
        ) {
            Navigation(navController = controller, viewModel= viewModel,paddingValue = it)
            AccountDialog(dialogOpen = dialogOpen)
        }
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

@Composable
fun ModalBottomSheetLayout(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = modifier.padding(16.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_heart_broken_24), contentDescription = "heart icon")
                Text(text = "Settings")
            }
        }
    }
}