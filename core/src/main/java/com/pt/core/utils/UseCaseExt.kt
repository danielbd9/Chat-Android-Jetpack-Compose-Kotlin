package com.pt.core.utils

interface UseCase<Params, Return> {
    suspend fun execute(params: Params?): Return
}