package mx.dev.shellcore.testinterview.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.dev.shellcore.testinterview.repository.TodoRepository
import mx.dev.shellcore.testinterview.repository.TodoRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class TodoRepositoryModule {

    @Binds
    abstract fun bindTodoListRepository(impl: TodoRepositoryImpl): TodoRepository
}