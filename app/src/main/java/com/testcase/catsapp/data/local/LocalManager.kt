package com.testcase.catsapp.data.local

import io.reactivex.Completable
import io.reactivex.Flowable

interface LocalManager {
    fun savePicture(picture: com.testcase.catsapp.domain.model.Picture): Flowable<Int>
}