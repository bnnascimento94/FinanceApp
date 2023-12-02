package com.vullpes.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.vullpes.financeapp.navigation.Screen
import com.vullpes.financeapp.navigation.SetupNavGraph
import com.vullpes.financeapp.ui.theme.FinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    firstDestination = Screen.Login.route,
                    navController = navController
                )
            }
        }
    }
}



