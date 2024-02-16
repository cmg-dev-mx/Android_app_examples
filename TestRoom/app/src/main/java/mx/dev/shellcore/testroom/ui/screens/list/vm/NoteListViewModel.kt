package mx.dev.shellcore.testroom.ui.screens.list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.dev.shellcore.testroom.core.model.NoteBo
import mx.dev.shellcore.testroom.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    private val _notes = MutableStateFlow(emptyList<NoteBo>())
    val notes = _notes.asStateFlow()

    fun getNotes() {
        viewModelScope.launch {
            repository.getNotes().collect {
                if (it.isSuccess) {
                    _notes.value = it.getOrNull() ?: emptyList()
                }
            }
        }
    }
}