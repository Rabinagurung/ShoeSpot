import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ramrojutta.ViewModel.MainViewModel
import com.example.ramrojutta.repository.DataRepository

class MainViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
