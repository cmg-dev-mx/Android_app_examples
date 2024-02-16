package mx.dev.shellcore.testinterview.web.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClient {

    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    val client: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}