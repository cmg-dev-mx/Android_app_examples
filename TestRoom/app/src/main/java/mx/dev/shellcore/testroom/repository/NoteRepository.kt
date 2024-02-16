package mx.dev.shellcore.testroom.repository

import kotlinx.coroutines.flow.Flow
import mx.dev.shellcore.testroom.core.model.NoteBo

interface NoteRepository {
    suspend fun getNotes(): Flow<Result<List<NoteBo>>>
    suspend fun getNoteById(id: Int): Flow<Result<NoteBo>>
    suspend fun insert(note: NoteBo): Flow<Result<Unit>>
    suspend fun update(note: NoteBo): Flow<Result<Unit>>
    suspend fun delete(note: NoteBo): Flow<Result<Unit>>
}