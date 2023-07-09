package com.example.atlasforms.common.data.http

import com.example.atlasforms.common.domain.HttpRequestConstants
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject

class BasicHttpReqests @Inject constructor(
    private val client: HttpClient,
) {

    suspend fun getProjectPeopleHttp(): HttpResponse {
        return client.get(HttpRequestConstants.getMain) {
            contentType(ContentType.Application.Json)
        }
    }
}
