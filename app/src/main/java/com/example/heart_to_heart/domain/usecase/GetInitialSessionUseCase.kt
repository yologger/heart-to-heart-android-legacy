package com.example.heart_to_heart.domain.usecase

import com.example.heart_to_heart.domain.base.BaseUseCase
import io.reactivex.Observable

class GetInitialSessionUseCase
constructor(

) : BaseUseCase<Boolean>() {
    override fun call(): Observable<Boolean> {
        return Observable.create { emitter ->
            emitter.onNext(true)
        }
    }
}