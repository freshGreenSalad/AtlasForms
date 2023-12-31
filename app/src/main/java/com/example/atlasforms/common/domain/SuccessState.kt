package com.example.atlasforms.common.domain

sealed class SuccessState<T> (val data: T? = null, val error: String? = null){
    class Failure<T>(error: String? = null): SuccessState<T>(error = error)
    class Success<T>(data: T? = null): SuccessState<T>(data = data)

    class Loading<T>(): SuccessState<T>()
}
