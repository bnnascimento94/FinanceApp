package com.vullpes.financeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.vullpes.util.navigation.Screen
import com.vullpes.financeapp.navigation.SetupNavGraph
import com.vullpes.financeapp.ui.theme.FinanceAppTheme
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
                    firstDestination = com.vullpes.util.navigation.Screen.Login.route,
                    navController = navController
                )
            }
        }
    }




}



