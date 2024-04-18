package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) : ViewModel() {

    fun liveData() = listLiveDataWrapper.liveData()

    fun save(bundle: BundleWrapper.Save) {
        listLiveDataWrapper.save(bundle)
    }

    fun restore(bundle: BundleWrapper.Restore) {
        listLiveDataWrapper.update(bundle.restore())
    }

    fun add(text: String) {
        listLiveDataWrapper.add(text)
    }
}

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                listLiveDataWrapper = ListLiveDataWrapper.Base()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}