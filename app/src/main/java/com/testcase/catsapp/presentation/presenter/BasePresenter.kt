package com.testcase.catsapp.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.testcase.catsapp.CatsApp
import com.testcase.catsapp.di.component.DaggerPresenterComponent
import com.testcase.catsapp.di.component.PresenterComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<T: MvpView> : MvpPresenter<T>() {

    protected val presenterComponent: PresenterComponent
        get() {
            return DaggerPresenterComponent
                .builder()
                .applicationComponent(CatsApp.applicationComponent)
                .build()
        }

    private val compositeDisposable = CompositeDisposable()

    protected fun unsubscribeOnDestroy(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}