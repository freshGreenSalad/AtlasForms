package com.example.atlasforms.common.data

interface EmailRepository {
    suspend fun sendEmail()
}