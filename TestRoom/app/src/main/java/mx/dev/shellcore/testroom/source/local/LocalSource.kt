package mx.dev.shellcore.testroom.source.local

import mx.dev.shellcore.testroom.db.entity.Note

interface LocalSource {
    suspend fun getAllNotes(): List<Note>
    suspend fun getNoteById(id: Int): Note
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
}