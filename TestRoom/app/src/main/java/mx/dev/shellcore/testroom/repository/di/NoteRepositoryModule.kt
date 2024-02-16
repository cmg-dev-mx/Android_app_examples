package mx.dev.shellcore.testroom.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.testroom.repository.NoteRepository
import mx.dev.shellcore.testroom.repository.NoteRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {

    @Binds
    abstract fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}