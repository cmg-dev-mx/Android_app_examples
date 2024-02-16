package mx.dev.shellcore.testroom.ui.route

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class BaseGraph(val route: String) {
    data object MainGraph : BaseGraph("main")
}

sealed class BaseRoute(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object NoteList : BaseRoute(route = "note_list")
    data object NoteDetail : BaseRoute(
        route = "note_detail/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )
    )
}