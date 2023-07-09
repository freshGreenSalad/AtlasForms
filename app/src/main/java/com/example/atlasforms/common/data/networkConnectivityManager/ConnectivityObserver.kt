package com.example.atlasforms.common.data.networkConnectivityManager

import kotlinx.coroutines.flow.Flow


interface ConnectivityObserver {

    fun observe(): Flow<Status>
    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}