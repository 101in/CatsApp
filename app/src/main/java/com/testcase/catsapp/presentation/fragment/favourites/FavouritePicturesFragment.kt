package com.testcase.catsapp.presentation.fragment.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.fragment.favourites.adapter.FavouritePicturesAdapter
import com.testcase.catsapp.presentation.presenter.FavouritePicturesPresenter

class FavouritePicturesFragment: MvpAppCompatFragment(), FavouritePicturesView {

    @InjectPresenter
    lateinit var favouritePicturesPresenter: FavouritePicturesPresenter

    private lateinit var emptyFavouritePicturesContainer: LinearLayout
    private lateinit var favouritePictures: RecyclerView
    private val favouritePicturesAdapter = FavouritePicturesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_pictures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        emptyFavouritePicturesContainer = view.findViewById(R.id.empty_favourite_pictures)
        favouritePictures = view.findViewById(R.id.favourite_pictures)
        initFavouritePictures()
    }

    private fun initFavouritePictures() {
        favouritePictures.layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        favouritePictures.adapter = favouritePicturesAdapter
    }

    override fun showFavourites(pictures: List<Picture>) {
        if (pictures.isEmpty()) {
            emptyFavouritePicturesContainer.isVisible = true
            favouritePictures.isVisible = false
        } else {
            emptyFavouritePicturesContainer.isVisible = false
            favouritePictures.isVisible = true
            favouritePicturesAdapter.setPictures(pictures)
        }
    }

    fun update() {
        favouritePicturesPresenter.updatePictures()
    }

    private companion object {
        private const val SPAN_COUNT = 2
    }
}