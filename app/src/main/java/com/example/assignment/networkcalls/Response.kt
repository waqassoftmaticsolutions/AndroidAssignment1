package com.example.assignment.networkcalls

sealed class Response<T>(val data: T? = null,val errorMessage: String? = null) {
    class Loading<T>(): Response<T>()
    class Success<T>(list: T? = null): Response<T>(data = list)
    class Error<T>(errorMessage: String):Response<T>(errorMessage = errorMessage)
}