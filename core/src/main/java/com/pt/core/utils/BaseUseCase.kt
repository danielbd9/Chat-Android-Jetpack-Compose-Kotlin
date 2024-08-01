package com.pt.core.utils

interface BaseUseCase<Params, Return> {
    suspend fun execute(params: Params): Return
}