package com.example.shoppingapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.common.Destinations
import com.example.shoppingapp.presentation.screens.home.HomeScreen
import com.example.shoppingapp.presentation.screens.login.LoginScreen
import com.example.shoppingapp.presentation.screens.signup.SignupScreen
import com.example.shoppingapp.presentation.screens.splash.SplashScreen
import com.example.shoppingapp.presentation.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH,

    ) {
        composable(Destinations.HOME) {
            HomeScreen()
        }
        composable(Destinations.SPLASH) {
            SplashScreen(navController = navController)
        }
        composable(Destinations.LOG_IN) {
            LoginScreen(navController = navController , onLoginSuccess = {
                navController.navigate(Destinations.HOME) {
                    popUpTo(Destinations.LOG_IN) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Destinations.SIGN_UP) {
            SignupScreen(
                navController = navController , onSignupSuccess = {
                    navController.navigate(Destinations.HOME){
                        popUpTo(Destinations.SIGN_UP) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        }

}

