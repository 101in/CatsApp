package com.testcase.catsapp.data.datasource.remote.mapper

import com.testcase.catsapp.data.datasource.remote.model.PictureDto
import com.testcase.catsapp.domain.model.Picture

fun mapPictureDtoToPicture(pictureDto: PictureDto): Picture {
    return Picture(
        pictureDto.id,
        pictureDto.url,
        pictureDto.width,
        pictureDto.height,
        false
    )
}