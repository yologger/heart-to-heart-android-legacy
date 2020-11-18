package com.example.heart_to_heart.presentation.base

import androidx.fragment.app.Fragment
import com.example.heart_to_heart.presentation.screen.AppActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment: Fragment() {

    val router: Router get() = (activity as AppActivity).router
    val disposables by lazy { CompositeDisposable() }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}