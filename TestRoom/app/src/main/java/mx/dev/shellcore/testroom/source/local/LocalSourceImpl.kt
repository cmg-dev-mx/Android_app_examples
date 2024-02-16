package mx.dev.shellcore.testroom.source.local

import mx.dev.shellcore.testroom.db.dao.NoteDao
import mx.dev.shellcore.testroom.db.entity.Note
import javax.inject.Inject

class LocalSourceImpl @Inject constructor(
    private val noteDao: NoteDao
) : LocalSource {

    override suspend fun getAllNotes(): List<Note> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return noteDao.getNoteById(id)
    }

    override suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun update(note: Note) {
        noteDao.update(note)
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}