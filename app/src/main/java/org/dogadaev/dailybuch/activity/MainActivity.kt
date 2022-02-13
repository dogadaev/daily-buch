package org.dogadaev.dailybuch.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.dogadaev.home.compose.HomeScreen
import org.dogadaev.navigation.compose.NavDestination
import org.dogadaev.ui.theme.CbtMateTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            CbtMateTheme {
                NavHost(
                    navController,
                    startDestination = NavDestination.Home.route
                ) {
                    composable(NavDestination.Home.route) {
                        HomeScreen()
                    }
                }
            }
        }
    }
}