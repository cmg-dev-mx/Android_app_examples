package mx.dev.shellcore.testroom.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BaseGraph.MainGraph.route) {
        mainGraph(navController = navController)
    }
}