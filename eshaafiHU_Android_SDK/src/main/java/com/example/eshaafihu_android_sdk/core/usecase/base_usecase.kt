package com.example.eshaafihu_android_sdk.core.usecase

abstract class BaseUseCase<Type, Params> {
    abstract suspend fun execute(params: Params): Type
}