
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.test.zoomclient.domain.model.User
import com.test.zoomclient.domain.model.repository.LoginRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import androidx.compose.runtime.*
import androidx.lifecycle.MutableLiveData
import com.test.zoomclient.presentation.ui.screens.login.Credentials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * UI state for the Login screen
 */
sealed interface LoginUiState {
    data class Authenticated(val user: User) : LoginUiState
    object Error : LoginUiState
    object Loading : LoginUiState
    object Initial : LoginUiState
}

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Initial)
    private set

    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading

            try {


                val user = loginRepository.login(credentials)
                loginUiState = LoginUiState.Authenticated(user)
                _isLoggedIn.value = true

            } catch (e: IOException) {
                loginUiState = LoginUiState.Error
            } catch (e: Exception) {
                e.printStackTrace()
                loginUiState = LoginUiState.Error

            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LoginViewModel(loginRepository = LoginRepository())
            }
        }
    }
}