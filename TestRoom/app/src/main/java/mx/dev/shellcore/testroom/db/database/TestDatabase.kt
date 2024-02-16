package mx.dev.shellcore.testroom.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.dev.shellcore.testroom.db.dao.NoteDao
import mx.dev.shellcore.testroom.db.entity.Note

@Database(
    entities = [
        Note::class
    ],
    version = 1
)
abstract class TestDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "test_database"
    }

    abstract fun noteDao(): NoteDao

}