package mx.dev.shellcore.testinterview.core.model

data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    var completed: Boolean
)
