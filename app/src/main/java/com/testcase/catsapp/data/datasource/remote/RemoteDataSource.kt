package com.testcase.catsapp.data.datasource.remote

import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Single

interface RemoteDataSource {
    fun fetchCats(page: Int, limit: Int): Single<List<Picture>>
}