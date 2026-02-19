package com.treceniomarvin.pokemonapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient() {
            install(ContentNegotiation) {
                gson()
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
        }
    }
}