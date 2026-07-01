package org.chevalierlab.kashier.core.network

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import org.chevalierlab.kashier.core.preferences.AppPreferences

object HttpClientFactory {
    fun create(
        engine: HttpClientEngine,
        preferences: AppPreferences
    ): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Network Log: $message")
                    }
                }
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val accessToken = preferences.getToken().firstOrNull()

                        if (accessToken.isNullOrBlank()) {
                            null
                        } else {
                            BearerTokens(
                                accessToken = accessToken,
                                refreshToken = ""
                            )
                        }
                    }
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 5000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}