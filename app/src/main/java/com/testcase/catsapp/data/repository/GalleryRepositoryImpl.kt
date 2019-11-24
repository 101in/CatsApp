package com.testcase.catsapp.data.repository

import com.testcase.catsapp.data.datasource.remote.RemoteDataSource
import com.testcase.catsapp.data.local.LocalManager
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.domain.repository.GalleryRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class GalleryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localManager: LocalManager
): GalleryRepository {

    override fun fetchCats(page: Int, limit: Int): Single<List<Picture>> {
        return remoteDataSource.fetchCats(page, limit)
    }

    override fun savePicture(picture: Picture): Flowable<Int> {
        return localManager.savePicture(picture)
    }
}