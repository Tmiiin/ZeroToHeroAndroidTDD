package ru.easycode.zerotoheroandroidtdd

import androidx.lifecycle.LiveData

interface LiveDataWrapper {

    class Base : Mutable {

        private val liveData: SingleLiveEvent<UiState> = SingleLiveEvent()
        override fun update(value: UiState) {
            liveData.value = value
        }

        override fun liveData(): LiveData<UiState> = liveData
        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }

    }

    interface Mutable: Save, Update, Observe

    interface Save {
        fun save(bundleWrapper: BundleWrapper.Save)
    }

    interface Update {
        fun update(value: UiState)
    }

    interface Observe {
        fun liveData(): LiveData<UiState>
    }
}