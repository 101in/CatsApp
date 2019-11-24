package com.testcase.catsapp.presentation.activity

import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.testcase.catsapp.R
import com.testcase.catsapp.presentation.fragment.favourites.FavouritePicturesFragment
import com.testcase.catsapp.presentation.fragment.gallery.GalleryFragment

class BottomNavigationItemSelectedListener(
    private val supportFragmentManager: FragmentManager,
    private val galleryFragment: GalleryFragment,
    private val favouritePicturesFragment: FavouritePicturesFragment
) : BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .show(galleryFragment)
                    .hide(favouritePicturesFragment)
                    .commit()
                return true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                    .show(favouritePicturesFragment)
                    .hide(galleryFragment)
                    .commit()
                favouritePicturesFragment.update()
                return true
            }
        }
        return false
    }

}