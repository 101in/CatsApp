package com.testcase.catsapp.presentation.fragment.gallery.list.holder

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.testcase.catsapp.R
import com.testcase.catsapp.presentation.fragment.gallery.callback.PartialReloadCallback
import kotlinx.android.synthetic.main.footer_view.view.*

class FooterHolder(itemView: View, partialReloadCallback: PartialReloadCallback) :
    RecyclerView.ViewHolder(itemView) {

    private val loadingProgressBar: ContentLoadingProgressBar = itemView.findViewById(R.id.partial_loading_progress_bar)
    private val errorLoadingText: TextView = itemView.findViewById(R.id.partial_loading_error_text)

    init {
        errorLoadingText.setOnClickListener {
            partialReloadCallback.onPartialReloadClicked()
        }
    }

    fun showLoading() {
        loadingProgressBar.isVisible = true
        errorLoadingText.isVisible = false
        loadingProgressBar.show()
    }

    fun showError() {
        loadingProgressBar.isVisible = false
        errorLoadingText.isVisible = true
        loadingProgressBar.hide()
    }
}