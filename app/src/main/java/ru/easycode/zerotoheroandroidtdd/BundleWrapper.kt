package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

sealed interface BundleWrapper {

    interface Mutable : BundleWrapper, Save, Restore

    interface Save : BundleWrapper {
        fun save(uiState: UiState)
    }

    interface Restore : BundleWrapper {
        fun restore(): UiState
    }

    class Base(private val bundle: Bundle) : Mutable {
        companion object {
            private const val KEY = "ui.state"
        }

        override fun save(uiState: UiState) {
            bundle.putSerializable(KEY, uiState)
        }

        override fun restore(): UiState {
            return if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)) {
                bundle.getSerializable(KEY, UiState::class.java)
            } else {
                bundle.getSerializable(KEY) as? UiState
            } ?: UiState.Initial
        }

    }
}