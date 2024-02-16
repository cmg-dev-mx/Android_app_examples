package mx.dev.shellcore.testinterview.web.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.dev.shellcore.testinterview.web.api.WebApi
import mx.dev.shellcore.testinterview.web.client.WebClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class WebModule {

    @Provides
    fun provideApi(retrofit: Retrofit): WebApi {
        return retrofit.create(WebApi::class.java)
    }

    @Provides
    fun provideRetrofit(): Retrofit {
        return WebClient.client
    }
}