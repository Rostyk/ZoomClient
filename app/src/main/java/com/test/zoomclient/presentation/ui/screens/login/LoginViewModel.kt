
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
import com.test.zoomclient.presentation.ui.screens.login.componenets.Credentials


/**
 * UI state for the Login screen
 */
sealed interface LoginUiState {
    data class Authenticated(val user: User) : LoginUiState
    object Error : LoginUiState
    object Loading : LoginUiState
}

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    var loginUiState: LoginUiState by mutableStateOf(LoginUiState.Loading)
    private set

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            loginUiState = LoginUiState.Loading

            loginUiState = try {
                LoginUiState.Authenticated(loginRepository.login(credentials))
            } catch (e: IOException) {
                LoginUiState.Error
            } catch (e: Exception) {
                LoginUiState.Error
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