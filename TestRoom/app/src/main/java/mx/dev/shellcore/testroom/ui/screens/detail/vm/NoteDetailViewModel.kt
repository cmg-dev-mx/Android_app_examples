package mx.dev.shellcore.testroom.ui.screens.detail.vm

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
class NoteDetailViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _note = MutableStateFlow(NoteBo(id = 0, title = "", content = ""))
    val note = _note.asStateFlow()

    private val _saved = MutableStateFlow(false)
    val saved = _saved.asStateFlow()

    fun updateNoteTitle(title: String) {
        _note.value = _note.value.copy(title = title)
    }

    fun updateNoteContent(content: String) {
        _note.value = _note.value.copy(content = content)
    }

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            repository.getNoteById(id).collect { result ->
                if (result.isSuccess) {
                    _note.value = result.getOrDefault(NoteBo(id = 0, title = "", content = ""))
                }
            }
        }
    }

    fun saveNote() {
        viewModelScope.launch {
            if (_note.value.id != 0) {
                repository.update(_note.value).collect {
                    if (it.isSuccess) {
                        _saved.value = true
                    }
                }
            } else {
                repository.insert(_note.value).collect {
                    if (it.isSuccess) {
                        _saved.value = true
                    }
                }
            }
        }
    }

    fun updateSaved(saved: Boolean) {
        _saved.value = saved
    }
}