package mx.dev.shellcore.testinterview.ui.screen.todolist.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.testinterview.core.model.Todo
import mx.dev.shellcore.testinterview.repository.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    private val _todoList = MutableStateFlow(listOf<Todo>())
    val todoList = _todoList.asStateFlow()

    fun fetchTodoList() {
        viewModelScope.launch {
            repository.fetchTodoList().collect {result ->
                result.onSuccess { list ->
                    _todoList.value = list
                }
            }
        }
    }
}