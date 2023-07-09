package com.example.atlasforms.common.data.http

import com.example.atlasforms.common.domain.HttpRequestConstants
import com.example.atlasforms.common.domain.SuccessState
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject

class BasicHttpReqests @Inject constructor(
    private val client: HttpClient,
) {

    suspend fun GetMainForm(): SuccessState<HttpResponse> {
        return try {
            SuccessState.Success(client.get(HttpRequestConstants.getMain) {
                contentType(ContentType.Application.Json)
            })
        } catch (e:Exception){
            SuccessState.Failure()
        }
    }
}
