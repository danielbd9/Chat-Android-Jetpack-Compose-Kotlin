package com.pt.common.utils

interface BaseUseCase<Params, Return> {
    suspend fun execute(params: Params): Return
}