package org.greenbyme.angelhack.ui.login.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.greenbyme.angelhack.extention.Event
import org.greenbyme.angelhack.network.ApiService

class SignUpRepository(val disposable: CompositeDisposable) {
    private val signInResponse = MutableLiveData<Event<Boolean>>()
    fun signUp(signUp: SignUpDTO? = null): LiveData<Event<Boolean>> {
        signUp?.let {
            ApiService.service.signUp(signUp.toJSON())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("asd", it.toString() + it.status + " " + it.data)
                    if (it.status == "200" || it.status == "201") {
                        ApiService.token = it.data
                        signInResponse.value = Event<Boolean>(true)
                    } else {
                        signInResponse.value = Event<Boolean>(false)
                    }
                }, {
                    signInResponse.value = Event<Boolean>(false)
                }).addTo(disposable)
        }
        return signInResponse
    }

    fun checkEmail(email: String): Single<Boolean> {
        return ApiService.service.emailCheck(email)
            .map { it.data.not() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun nicknameEmail(nickname: String): Single<Boolean> {
        return ApiService.service.nicknameCheck(nickname)
            .map { it.data.not() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}