package mx.dev.shellcore.testroom.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import mx.dev.shellcore.testroom.db.entity.Note

@Dao
interface NoteDao {

    // Get all notes
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Note>

    // Get note by id
    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): Note

    // Insert a note
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    // Update a note
    @Update
    suspend fun update(note: Note)

    // Delete a note
    @Delete
    suspend fun delete(note: Note)
}