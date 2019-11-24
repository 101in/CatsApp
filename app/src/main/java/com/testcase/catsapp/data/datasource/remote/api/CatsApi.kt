package com.testcase.catsapp.data.datasource.remote.api

import com.testcase.catsapp.data.datasource.remote.model.PictureDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatsApi {
    @GET("/v1/images/search")
    fun fetch(@Query("page") page: Int, @Query("limit") limit: Int): Single<List<PictureDto>>
}