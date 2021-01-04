package org.greenbyme.angelhack.ui.login.signin

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import java.util.regex.Pattern

class SignInViewModel(
    val repository: SignUpRepository
) : ViewModel() {
    private val TAG = SignInViewModel::class.simpleName
    private val email: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    val emailValid: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val password: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    val passwordValid: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val passwordCheck: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    val passwordCheckValid: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val nickname: BehaviorSubject<String> = BehaviorSubject.createDefault("")
    val nicknameValid: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val serviceTerms: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    val serviceTermsValid: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    private val marketingTerms: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val isNext = repository.signUp()

    val isValidComplete: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

    val disposable = CompositeDisposable()

    init {
        disposable.add(
            Observable.combineLatest(
                isEmailValid(),
                isEmailValid(),
                isValidPasswordCheck(),
                isValidNickName(),
                serviceTerms,
                { emailResult: Boolean, passwordResult: Boolean, passwordCheckResult: Boolean, nicknameResult: Boolean, serviceResult: Boolean ->
                    emailResult && passwordResult && passwordCheckResult && nicknameResult && serviceResult
                })
                .subscribe(
                    isValidComplete::onNext, this::throwMessage
                )
        )

        disposable.addAll(
            isEmailValid()
                .subscribe(emailValid::onNext, this::throwMessage),
            isValidPassword()
                .subscribe(passwordValid::onNext, this::throwMessage),
            isValidPasswordCheck()
                .subscribe(passwordCheckValid::onNext, this::throwMessage),
            isValidNickName()
                .subscribe(nicknameValid::onNext, this::throwMessage),
            serviceTerms
                .subscribe(serviceTermsValid::onNext, this::throwMessage)
        )
    }

    private fun getSignData(): SignUpDTO {
        return SignUpDTO(
            email = email.value ?: "",
            password = password.value ?: "",
            nickname = nickname.value ?: "",
            marketingTerms = marketingTerms.value ?: false
        )
    }

    fun onClickSignIn(view: View) {
        if (isValidComplete.value == true) {
            repository.signUp(getSignData())
        } else {
            allValidCheck()
        }
    }

    private fun allValidCheck() {
        email.value?.let { email.onNext(it) }
        password.value?.let { password.onNext(it) }
        passwordCheck.value?.let { passwordCheck.onNext(it) }
        nickname.value?.let { nickname.onNext(it) }
        serviceTerms.value?.let { serviceTerms.onNext(it) }
    }

    private fun isEmailValid(): Observable<Boolean> {
        return email.flatMap {
            val emailRegex: String = "\\w+@\\w+\\.\\w+(\\.\\w+)?"
            if (Pattern.matches(emailRegex, it)) {
                return@flatMap repository.checkEmail(it).toObservable()
            }
            return@flatMap Observable.just(false)
        }
    }

    private fun isValidPassword(): Observable<Boolean> {
        return password.map {
            val passwordRegex: String = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[$@\$!%*#?&a-zA-Z\\d]{8,}$"
            if (Pattern.matches(passwordRegex, it)) {
                return@map true
            }
            return@map false
        }
    }
    private fun isValidPasswordCheck(): Observable<Boolean> {
        return Observable.combineLatest(
            password,
            passwordCheck,
            { passwordSrc, passwordDes -> passwordDes == passwordSrc })

    }

    private fun isValidNickName(): Observable<Boolean> {
        return nickname.flatMap {
            if (it.length in 2..8) {
                return@flatMap repository.nicknameEmail(it).toObservable()
            }
            return@flatMap Observable.just(false)
        }
    }

    /**
     *  Getter, Setter
     */
    fun getEmail(): String? = email.value
    fun setEmail(email: String) {
        this.email.onNext(email)
    }

    fun getPassword(): String? = password.value
    fun setPassword(password: String) {
        this.password.onNext(password)
    }

    fun getPasswordCheck(): String? = passwordCheck.value
    fun setPasswordCheck(passwordCheck: String) {
        this.passwordCheck.onNext(passwordCheck)
    }

    fun getNickName(): String? = nickname.value
    fun setNickName(nickname: String) {
        this.nickname.onNext(nickname)
    }

    fun getServiceTerms(): Boolean? = serviceTerms.value
    fun setServiceTerms(isChecked: Boolean?) {
        this.serviceTerms.onNext(isChecked ?: false)
    }

    fun getMarketingTerms(): Boolean? = marketingTerms.value
    fun setMarketingTerms(isChecked: Boolean?) {
        this.marketingTerms.onNext(isChecked ?: false)
    }

    private fun throwMessage(e: Throwable) {
        Log.e(TAG, e.localizedMessage ?: "")
    }

}