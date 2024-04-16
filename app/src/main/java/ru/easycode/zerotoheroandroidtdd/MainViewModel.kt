package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.easycode.zerotoheroandroidtdd.data.Repository
import ru.easycode.zerotoheroandroidtdd.data.SimpleService

class MainViewModel(val liveDataWrapper: LiveDataWrapper.Mutable, val repository: Repository) :
    ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    fun load() {
        liveDataWrapper.update(UiState.ShowProgress)
        viewModelScope.launch {
            val response = repository.load()
            response.show(liveDataWrapper)
        }
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) {
        val uiState = bundleWrapper.restore()
        liveDataWrapper.update(uiState)
    }

    fun liveData() = liveDataWrapper.liveData()
}

class MainViewModelFactory : ViewModelProvider.Factory {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://google.com").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(LiveDataWrapper.Base(), Repository.Base(retrofit.create(SimpleService::class.java), URL)) as T
        } else {
            throw IllegalStateException("ViewModel of ${modelClass} not found")
        }
    }

    companion object {
        private const val URL =
            "https://raw.githubusercontent.com/JohnnySC/ZeroToHeroAndroidTDD/task/018-clouddatasource/app/sampleresponse.json"
    }
}