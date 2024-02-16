package mx.dev.shellcore.testroom.source.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.testroom.db.dao.NoteDao
import mx.dev.shellcore.testroom.db.database.TestDatabase
import mx.dev.shellcore.testroom.source.local.LocalSource
import mx.dev.shellcore.testroom.source.local.LocalSourceImpl

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {

    @Provides
    fun provideLocalSourceImpl(noteDao: NoteDao): LocalSource {
        return LocalSourceImpl(noteDao = noteDao)
    }

    @Provides
    fun provideNoteDao(database: TestDatabase): NoteDao {
        return database.noteDao()
    }

    @Provides
    fun provideTestDatabase(@ApplicationContext context: Context): TestDatabase {
        return Room.databaseBuilder(
            context,
            TestDatabase::class.java,
            TestDatabase.DATABASE_NAME
        ).build()
    }
}