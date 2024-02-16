package mx.dev.shellcore.testinterview.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.testinterview.core.model.Todo
import mx.dev.shellcore.testinterview.web.api.WebApi
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val webApi: WebApi
) : TodoRepository {

    override suspend fun fetchTodoList(): Flow<Result<List<Todo>>> {
        val list = webApi.getTodos()
        return flow {
            emit(Result.success(list))
        }
    }

    override suspend fun fetchTodoDetailById(id: Int): Flow<Result<Todo>> {
        val todo = webApi.getTodoById(id)
        return flow {
            emit(Result.success(todo))
        }
    }
}