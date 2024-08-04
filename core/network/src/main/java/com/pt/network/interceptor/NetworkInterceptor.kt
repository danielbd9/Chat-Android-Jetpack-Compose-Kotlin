package com.pt.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            when (response.code) {
                400 -> throw NetworkException.BadRequestException("Bad Request")
                401 -> throw NetworkException.UnauthorizedException("Unauthorized")
                403 -> throw NetworkException.ForbiddenException("Forbidden")
                404 -> throw NetworkException.NotFoundException("Not Found")
                500 -> throw NetworkException.ServerErrorException("Server Error")
                else -> throw NetworkException.ApiException("Unknown API error")
            }
        }

        return response
    }
}

sealed class NetworkException(message: String) : IOException(message) {
    class BadRequestException(message: String) : NetworkException(message)
    class UnauthorizedException(message: String) : NetworkException(message)
    class ForbiddenException(message: String) : NetworkException(message)
    class NotFoundException(message: String) : NetworkException(message)
    class ServerErrorException(message: String) : NetworkException(message)
    class ApiException(message: String) : NetworkException(message)
}