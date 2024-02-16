package mx.dev.shellcore.testinterview.repository

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.testinterview.core.model.Todo

interface TodoRepository {
    suspend fun fetchTodoList(): Flow<Result<List<Todo>>>
    suspend fun fetchTodoDetailById(id: Int): Flow<Result<Todo>>
}
