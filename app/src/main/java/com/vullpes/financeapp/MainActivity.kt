package com.vullpes.financeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.vullpes.financeapp.navigation.SetupNavGraph
import com.vullpes.components.ui.theme.FinanceAppTheme
import com.vullpes.common.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    firstDestination = com.vullpes.common.navigation.Screen.Login.route,
                    navController = navController
                )
            }
        }
    }




}



