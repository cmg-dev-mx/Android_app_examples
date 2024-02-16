package mx.dev.shellcore.testinterview.ui.screen.tododetail.vm

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
class TodoDetailViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {

    private val _todo = MutableStateFlow<Todo?>(null)
    val todo = _todo.asStateFlow()

    fun getTodoDetailById(id: Int) {
        viewModelScope.launch {
            repository.fetchTodoDetailById(id).collect { result ->
                result.onSuccess {
                    _todo.value = it
                }
            }
        }
    }
}