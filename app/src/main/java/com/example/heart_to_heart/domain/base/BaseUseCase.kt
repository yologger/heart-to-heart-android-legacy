package com.example.heart_to_heart.domain.base

import com.example.heart_to_heart.domain.transformer.AsyncTransformer
import io.reactivex.Observable

abstract class BaseUseCase<T>
constructor(
) {
    protected abstract fun call(): Observable<T>

    fun execute(): Observable<T> {
        return this.call()
            .compose(AsyncTransformer())
    }
}