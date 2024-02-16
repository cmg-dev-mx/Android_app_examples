package mx.dev.shellcore.testinterview.web.api

import mx.dev.shellcore.testinterview.core.model.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): Todo
}