package com.testcase.catsapp.data.datasource.remote

import com.testcase.catsapp.data.datasource.remote.api.CatsApi
import com.testcase.catsapp.data.datasource.remote.mapper.mapPictureDtoToPicture
import com.testcase.catsapp.domain.model.Picture
import io.reactivex.Single

class RemoteDataSourceImpl(
    private val catsApi: CatsApi
): RemoteDataSource {
    override fun fetchCats(page: Int, limit: Int): Single<List<Picture>> {
        return catsApi.fetch(page, limit).map {
            picsDto -> picsDto.map(::mapPictureDtoToPicture)
        }
    }
}