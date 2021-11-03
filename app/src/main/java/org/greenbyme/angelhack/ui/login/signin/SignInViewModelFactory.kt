

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.greenbyme.angelhack.ui.login.signin.SignUpRepository
import org.greenbyme.angelhack.ui.login.signin.SignInViewModel

class SignInViewModelFactory(private val repository: SignUpRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            SignInViewModel(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}