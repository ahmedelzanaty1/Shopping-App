package com.example.shoppingapp.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.shoppingapp.R
import com.example.shoppingapp.common.Destinations


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    LaunchedEffect(state) {
        when (state) {
            is SplashViewModel.SplashState.Authenticated -> {
                // User is logged in, navigate to home
                navController.navigate(Destinations.HOME) {
                    popUpTo(Destinations.SPLASH) {
                        inclusive = true
                    }
                }
            }
            is SplashViewModel.SplashState.NotAuthenticated -> {
                // User is not logged in, navigate to intro
                navController.navigate(Destinations.LOG_IN) {
                    popUpTo(Destinations.SPLASH) {
                        inclusive = true
                    }
                }
            }
            SplashViewModel.SplashState.Loading -> {
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.splash),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }
}