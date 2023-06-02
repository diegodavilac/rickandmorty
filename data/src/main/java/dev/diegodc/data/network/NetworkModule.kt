package dev.diegodc.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.diegodc.data.network.api.RickAndMortyApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Provides
    @Singleton
    fun provideRickAndMortyApi(httpClient: HttpClient):RickAndMortyApi {
        return RickAndMortyApiImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideHttpClient() : HttpClient{
        return HttpClient(Android){
            expectSuccess = true

            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "rickandmortyapi.com"
                }

                headers {
                    append(HttpHeaders.Connection, "Keep-Alive")
                    append(HttpHeaders.Accept, "*/*")
                    append(HttpHeaders.AcceptCharset, "UTF-8")
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        explicitNulls = false
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}