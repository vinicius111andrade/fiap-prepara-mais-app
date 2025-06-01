package com.vdemelo.preparamais.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vdemelo.preparamais.ui.home.HomeScreen
import com.vdemelo.preparamais.ui.personalization.PersonalizationScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
    ) {
    NavHost(navController = navController, startDestination = NavRoutes.HOME) {
        composable(NavRoutes.HOME) {
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.PERSONALIZATION) {
            PersonalizationScreen(navController = navController)
        }
    }
}
