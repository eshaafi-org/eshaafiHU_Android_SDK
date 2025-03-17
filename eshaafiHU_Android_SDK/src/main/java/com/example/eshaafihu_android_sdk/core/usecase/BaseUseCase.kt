package com.example.eshaafihu_android_sdk.core.usecase

abstract class BaseUseCase<Type, Params> {
    abstract suspend fun execute(params: Params): Type

    // Overloaded function for use cases that don't require parameters
    suspend fun execute(): Type {
        return execute(Unit as Params)
    }
}