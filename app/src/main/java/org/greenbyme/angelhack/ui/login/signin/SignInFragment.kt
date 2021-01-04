package org.greenbyme.angelhack.ui.login.signin

import SignInViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.reactivex.disposables.CompositeDisposable
import org.greenbyme.angelhack.R
import org.greenbyme.angelhack.databinding.FragmentSignInBinding
import org.greenbyme.angelhack.ui.BaseFragment

class SignInFragment : BaseFragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    private var isValidation = false
    private var autoClearDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        doObserve()
    }

    private fun initView() {
        viewModel = ViewModelProvider(
            this,
            SignInViewModelFactory(SignUpRepository(autoClearDisposable))
        ).get(SignInViewModel::class.java)
        autoClearDisposable.add(viewModel.disposable)
        binding.signinVm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun doObserve() {
        //data observe
        viewModel.isNext.observe(viewLifecycleOwner, Observer {
            if (it.peekContent()) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.setupProgileFragment)
            } else {
                showServiceTermsValidCaption(viewModel.getServiceTerms() ?: false)
            }
        })

        autoClearDisposable.addAll(
            viewModel.isValidComplete
                .subscribe(this::setCompleteButton, this::throwError),
            viewModel.emailValid
                .skip(INIT_SKIP_COUNT)
                .subscribe(this::showEmailValidCaption, this::throwError),
            viewModel.passwordValid
                .skip(INIT_SKIP_COUNT)
                .subscribe(this::showPasswordValidCaption, this::throwError),
            viewModel.passwordCheckValid
                .skip(INIT_SKIP_COUNT)
                .subscribe(this::showPasswordCheckValidCaption, this::throwError),
            viewModel.nicknameValid
                .skip(INIT_SKIP_COUNT)
                .subscribe(this::showNicknameValidCaption, this::throwError),
            viewModel.serviceTermsValid
                .skip(INIT_SKIP_COUNT)
               // .filter { it }
                .subscribe(this::showServiceTermsValidCaption)
        )
    }

    private fun setCompleteButton(it: Boolean) {
        binding.btnSigninComplete.isActivated = it
        isValidation = it
    }

    private fun showEmailValidCaption(isValid: Boolean) {
        binding.tvSigninEmailValidation.visibility = isValid.not().convertViewVisible()
        binding.imgSigninEmailValidation.visibility = isValid.convertViewVisible()
    }

    private fun showPasswordValidCaption(isValid: Boolean) {
        binding.imgSigninPasswordValidation.visibility = isValid.convertViewVisible()
        binding.tvSigninPasswordValidationCaption.setTextColor(getValidTextColor(isValid))
    }

    private fun showPasswordCheckValidCaption(isValid: Boolean) {
        if (binding.etSigninPasswordCheck.text.isNotBlank()) {
            binding.tvSigninPasswordCheckValidationCaption.visibility =
                isValid.not().convertViewVisible()
            binding.imgSigninPasswordCheckValidation.visibility =
                isValid.convertViewVisible()
        }
    }

    private fun showNicknameValidCaption(isValid: Boolean) {
        binding.imgSigninNicknameValidation.visibility = isValid.convertViewVisible()
        binding.tvSigninNicknameValidationCaption.setTextColor(getValidTextColor(isValid))
    }

    private fun showServiceTermsValidCaption(isValid: Boolean) {
        binding.tvSigninServiceTermsValidationCaption.visibility =
            isValid.not().convertViewVisible()
    }


    private fun getValidTextColor(isValid: Boolean): Int {
        return if (isValid) {
            resources.getColor(R.color.text_gray)
        } else {
            resources.getColor(R.color.invalid_caption_color)
        }
    }


    companion object {
        private const val INIT_SKIP_COUNT: Long = 1
    }
}

fun Boolean.convertViewVisible(): Int {
    return if (this) {
        View.VISIBLE
    } else {
        View.GONE
    }
}