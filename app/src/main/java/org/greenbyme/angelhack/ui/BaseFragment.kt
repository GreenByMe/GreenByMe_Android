package org.greenbyme.angelhack.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseFragment : Fragment() {
    private var mAllSubscription = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable) {
        if (mAllSubscription.isDisposed) {
            mAllSubscription = CompositeDisposable()
        }

        mAllSubscription.add(disposable)
    }

    private fun disposeSubscription() {
        try {
            mAllSubscription.dispose()
        } catch (ignored: Exception) {
        }
    }

    override fun onDestroy() {
        disposeSubscription()
        super.onDestroy()
    }

}