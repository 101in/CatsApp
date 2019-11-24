package com.testcase.catsapp.presentation.fragment.gallery

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.testcase.catsapp.R
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.presentation.fragment.gallery.callback.PartialLoadingCallback
import com.testcase.catsapp.presentation.fragment.gallery.callback.PictureViewCallback
import com.testcase.catsapp.presentation.fragment.gallery.list.GalleryAdapter
import com.testcase.catsapp.presentation.fragment.gallery.list.PaginationListener
import com.testcase.catsapp.presentation.presenter.GalleryPresenter
import com.testcase.catsapp.presentation.utils.DownloadPictureState
import com.testcase.catsapp.presentation.utils.GalleryState
import com.testcase.catsapp.utils.startSettingsActivity
import io.reactivex.disposables.Disposable


class GalleryFragment : MvpAppCompatFragment(), GalleryView {

    @InjectPresenter
    lateinit var galleryPresenter: GalleryPresenter

    private lateinit var gallery: RecyclerView
    private val galleryAdapter: GalleryAdapter

    private lateinit var galleryProgressContainer: FrameLayout
    private lateinit var galleryLoadingProgressBar: ContentLoadingProgressBar
    private lateinit var galleryLoadingErrorContainer: LinearLayout
    private lateinit var galleryLoadingReload: Button
    private lateinit var gallerySwipeRefresh: SwipeRefreshLayout

    private var downloadDialog: AlertDialog? = null
    private var permissionDisposable: Disposable? = null
    private var isLoading = false

    init {
        val pictureViewCallback = object : PictureViewCallback {
            override fun onPictureFavouriteButtonClicked(picture: Picture) {
                galleryPresenter.togglePictureAsFavourite(picture)
            }

            override fun onPictureDownloadButtonClicked(picture: Picture) {
                permissionDisposable = RxPermissions(this@GalleryFragment)
                    .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe { permission ->
                        processPermission(permission, picture)
                    }
            }
        }
        val partialLoadingCallback = object : PartialLoadingCallback {
            override fun onNewPageRequested() {
                galleryPresenter.fetchNextPage()
            }
        }
        galleryAdapter = GalleryAdapter(pictureViewCallback, partialLoadingCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gallery = view.findViewById(R.id.gallery)
        galleryProgressContainer = view.findViewById(R.id.gallery_progress_container)
        galleryLoadingProgressBar = view.findViewById(R.id.gallery_loading_progress_bar)
        galleryLoadingErrorContainer = view.findViewById(R.id.gallery_loading_error_container)
        galleryLoadingReload = view.findViewById(R.id.gallery_loading_reload)
        gallerySwipeRefresh = view.findViewById(R.id.gallery_refresh)
        initListeners()
        initGallery()
    }

    private fun initListeners() {
        gallerySwipeRefresh.setOnRefreshListener {
            galleryPresenter.fetchFreshData()
        }
        galleryLoadingReload.setOnClickListener {
            galleryPresenter.fetchFreshData()
        }
    }

    private fun initGallery() {
        val gridLayoutManager = GridLayoutManager(context, DEFAULT_SPAN_COUNT)
        gallery.layoutManager = gridLayoutManager
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (galleryAdapter.isErrorLoadingItem(position)) {
                    DEFAULT_SPAN_COUNT
                } else {
                    IMAGE_SPAN_COUNT
                }
            }
        }
        gallery.adapter = galleryAdapter
        gallery.addOnScrollListener(object : PaginationListener(gridLayoutManager) {
            override fun isLoading() = isLoading

            override fun loadMoreItems() {
                isLoading = true
                gallery.post {
                    galleryAdapter.showPartialLoading()
                }
                galleryPresenter.fetchNextPage()
            }

        })
    }

    override fun togglePictureAsFavourite(position: Int, picture: Picture) {
        galleryAdapter.togglePictureAsFavourite(position, picture)
    }

    override fun showSaveProgressDialog(downloadPictureState: DownloadPictureState) {
        when (downloadPictureState) {
            is DownloadPictureState.Loading -> {
                showDialogMessage(
                    getString(
                        R.string.download_dialog_loading_text,
                        downloadPictureState.progress
                    )
                )
            }
            is DownloadPictureState.Success -> {
                showDialogMessage(getString(R.string.download_dialog_success_text))
            }
            is DownloadPictureState.Error -> {
                showDialogMessage(getString(R.string.download_dialog_error_text))
            }
        }
    }

    private fun showDialogMessage(
        text: String,
        alertDialogSettings: AlertDialog.Builder.() -> Unit = {}
    ) {
        if (downloadDialog == null) {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
                .setMessage(text)
                .setOnDismissListener {
                    downloadDialog = null
                }
                .setOnCancelListener {
                    downloadDialog = null
                }.apply(alertDialogSettings)
            downloadDialog = alertDialogBuilder.show()
        } else {
            downloadDialog!!.setMessage(text)
        }
    }

    override fun processGalleryState(galleryState: GalleryState) {
        isLoading = false
        if (gallerySwipeRefresh.isRefreshing) {
            gallerySwipeRefresh.isRefreshing = false
        }
        when (galleryState) {
            is GalleryState.Loading -> showProgress()
            is GalleryState.ErrorLoading -> showConnectionError()
            is GalleryState.Success -> showGallery(galleryState.pictures)
        }
    }

    override fun processPartialLoadingError(show: Boolean) {
        galleryAdapter.processPartialLoadingError(show)
    }

    override fun onStop() {
        super.onStop()
        permissionDisposable?.dispose()
    }

    private fun processPermission(
        permission: Permission,
        picture: Picture
    ) {
        when {
            permission.granted -> galleryPresenter.downloadPicture(picture)
            permission.shouldShowRequestPermissionRationale ->
                showDialogMessage(getString(R.string.download_dialog_permission_denied_text))
            else -> showDialogMessage(getString(R.string.download_dialog_permission_rationale_text)) {
                setPositiveButton(R.string.download_dialog_permission_positive_button) { dialog, _ ->
                    requireContext().startSettingsActivity()
                    dialog.dismiss()
                }
            }
        }
    }

    private fun showGallery(pictures: List<Picture>) {
        gallery.isVisible = true
        galleryLoadingProgressBar.hide()
        galleryAdapter.setPictures(pictures)
        galleryProgressContainer.isVisible = false
    }

    private fun showProgress() {
        gallery.isVisible = false
        galleryProgressContainer.isVisible = true
        galleryLoadingProgressBar.show()
        galleryLoadingErrorContainer.isVisible = false
    }

    private fun showConnectionError() {
        gallery.isVisible = false
        galleryLoadingProgressBar.hide()
        galleryProgressContainer.isVisible = true
        galleryLoadingErrorContainer.isVisible = true
    }

    private companion object {
        private const val DEFAULT_SPAN_COUNT = 2
        private const val IMAGE_SPAN_COUNT = 1
    }

}