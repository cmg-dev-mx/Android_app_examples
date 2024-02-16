package mx.dev.shellcore.testinterview.ui.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import mx.dev.shellcore.testinterview.ui.screen.tododetail.layout.TodoDetailLayout
import mx.dev.shellcore.testinterview.ui.screen.todolist.layout.TodoListLayout

fun NavGraphBuilder.mainGraph(navController: NavController? = null) {
    navigation(
        route = BaseGraph.TodoList.route,
        startDestination = BaseRoute.TodoList.route
    ) {
        composable(route = BaseRoute.TodoList.route) { TodoListLayout(navController) }
        composable(
            route = BaseRoute.TodoDetail.route,
            arguments = BaseRoute.TodoDetail.arguments
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 0
            TodoDetailLayout(id = id)
        }
    }
}