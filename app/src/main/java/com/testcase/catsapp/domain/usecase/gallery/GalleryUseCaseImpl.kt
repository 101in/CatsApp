package com.testcase.catsapp.domain.usecase.gallery

import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.domain.repository.FavouritePicturesRepository
import com.testcase.catsapp.domain.repository.GalleryRepository
import io.reactivex.Completable

class GalleryUseCaseImpl(
    private val galleryRepository: GalleryRepository,
    private val favouritePicturesRepository: FavouritePicturesRepository
) : GalleryUseCase {
    override fun fetchCats(page: Int, limit: Int) = galleryRepository.fetchCats(page, limit)
    override fun savePicture(picture: Picture) = galleryRepository.savePicture(picture)
    override fun togglePictureAsFavourite(picture: Picture): Completable {
        return if (picture.isFavourite) {
            favouritePicturesRepository.removePictureFromFavourite(picture)
        } else {
            favouritePicturesRepository.markPictureAsFavourite(picture)
        }
    }
}
