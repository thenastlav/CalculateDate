package com.example.calculate.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.calculate.Navigation.NavScreen
import com.example.calculate.Navigation.Screen
import com.example.calculate.R

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTheme {
                val navCtrl = rememberNavController()
                val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = when(navBackStackEntry?.destination?.route){
                                    Screen.FIRST_SCREEN.route -> R.string.first_screen
                                    Screen.SECOND_SCREEN.route -> R.string.second_screen
                                    else -> R.string.app_name
                                }))
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    }
                ) {
                    Surface(
                        modifier = Modifier.padding(it).fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavScreen(navController = navCtrl)
                    }
                }
            }
        }
    }
}

