package mx.dev.shellcore.testroom.repository

import kotlinx.coroutines.flow.flow
import mx.dev.shellcore.testroom.core.model.NoteBo
import mx.dev.shellcore.testroom.db.entity.Note
import mx.dev.shellcore.testroom.source.local.LocalSource
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val source: LocalSource
) : NoteRepository {

    override suspend fun getNotes() = flow {
        val notes = source.getAllNotes().mapToListBo()
        emit(Result.success(notes))
    }

    override suspend fun getNoteById(id: Int) = flow {
        val note = source.getNoteById(id).mapToBo()
        emit(Result.success(note))
    }

    override suspend fun insert(note: NoteBo) = flow {
        try {
            source.insert(note.mapToEntity())
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun update(note: NoteBo) = flow {
        try {
            source.update(note.mapToEntity())
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun delete(note: NoteBo) = flow {
        try {
            source.delete(note.mapToEntity())
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}

private fun Note.mapToBo() = NoteBo(
    id,
    title?:"",
    content?:""
)

private fun NoteBo.mapToEntity() = Note(
    id,
    title,
    content
)

private fun List<Note>.mapToListBo() = map {
    it.mapToBo()
}