package mx.dev.shellcore.testroom.ui.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.testroom.ui.screens.detail.layout.NoteDetailLayout
import mx.dev.shellcore.testroom.ui.screens.list.layout.NoteListLayout

fun NavGraphBuilder.mainGraph(navController: NavController? = null) {
    navigation(route = BaseGraph.MainGraph.route, startDestination = BaseRoute.NoteList.route) {
        composable(route = BaseRoute.NoteList.route) { NoteListLayout(navController = navController) }
        composable(
            route = BaseRoute.NoteDetail.route,
            arguments = BaseRoute.NoteDetail.arguments
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
            NoteDetailLayout(navController = navController, id = id)
        }
    }
}