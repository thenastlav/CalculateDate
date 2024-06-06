package com.example.calculate.Navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calculate.Screens.DateDo
import com.example.calculate.Screens.DateDo2

@Composable
fun NavScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.FIRST_SCREEN.route
    ){
        composable(Screen.FIRST_SCREEN.route){
            DateDo(
                navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Screen.SECOND_SCREEN.route){
            DateDo2(
                navController,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}