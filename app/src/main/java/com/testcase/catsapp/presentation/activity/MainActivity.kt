package com.testcase.catsapp.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.testcase.catsapp.R
import com.testcase.catsapp.presentation.fragment.favourites.FavouritePicturesFragment
import com.testcase.catsapp.presentation.fragment.gallery.GalleryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val galleryFragment: GalleryFragment
        val favouritePicturesFragment: FavouritePicturesFragment

        if (savedInstanceState == null) {
            galleryFragment = GalleryFragment()
            favouritePicturesFragment = FavouritePicturesFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, galleryFragment, GALLERY_TAG)
                .add(R.id.fragment_container, favouritePicturesFragment, FAVOURITES_TAG)
                .hide(favouritePicturesFragment)
                .commit()
        } else {
            galleryFragment = supportFragmentManager.findFragmentByTag(GALLERY_TAG) as GalleryFragment
            favouritePicturesFragment = supportFragmentManager.findFragmentByTag(FAVOURITES_TAG) as FavouritePicturesFragment
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationItemSelectedListener(
            supportFragmentManager,
            galleryFragment,
            favouritePicturesFragment
        ))
    }

    companion object {
        private const val GALLERY_TAG = "gallery"
        private const val FAVOURITES_TAG = "favourites"
    }
}
