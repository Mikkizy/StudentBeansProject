package com.lord_ukaka.projectbymiracle.presentation.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lord_ukaka.projectbymiracle.R
import com.lord_ukaka.projectbymiracle.presentation.Screen
import com.lord_ukaka.projectbymiracle.presentation.photos.components.ListContent
import com.lord_ukaka.projectbymiracle.presentation.photos.components.PhotosTopBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel = hiltViewModel(),
    navController: NavController
) {

    val state = viewModel.state.value
    val albumsList = state.albums

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is PhotosEvents.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            PhotosTopBar {
                navController.navigate(Screen.LoginScreen.route)
            }
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            ListContent(items = albumsList)
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            if (state.isError) {
                Column {
                    Text(
                        text = stringResource(id = R.string.error),
                        color = Color.Red,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { viewModel.loadAlbums() },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0,138,0),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}