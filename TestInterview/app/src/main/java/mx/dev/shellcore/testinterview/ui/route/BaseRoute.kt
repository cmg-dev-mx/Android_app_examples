package mx.dev.shellcore.testinterview.ui.route

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class BaseGraph(val route: String) {
    data object TodoList : BaseGraph("main")
}

sealed class BaseRoute(val route: String, val arguments:List<NamedNavArgument> = emptyList()) {
    data object TodoList : BaseRoute("todo_list")
    data object TodoDetail : BaseRoute(
        route = "todo_detail/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )
}