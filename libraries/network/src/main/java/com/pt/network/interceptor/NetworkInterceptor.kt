package com.pt.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException

class NetworkInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            when (response.code) {
                400 -> throw BadRequestException("Bad Request")
                401 -> throw UnauthorizedException("Unauthorized")
                403 -> throw ForbiddenException("Forbidden")
                404 -> throw NotFoundException("Not Found")
                500 -> throw ServerErrorException("Server Error")
                else -> throw ApiException("Unknown API error")
            }
        }

        return response
    }
}

class BadRequestException(message: String) : IOException(message)
class UnauthorizedException(message: String) : IOException(message)
class ForbiddenException(message: String) : IOException(message)
class NotFoundException(message: String) : IOException(message)
class ServerErrorException(message: String) : IOException(message)
class ApiException(message: String) : IOException(message)


