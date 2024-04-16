package ru.easycode.zerotoheroandroidtdd.data

import ru.easycode.zerotoheroandroidtdd.LiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.SimpleResponse
import ru.easycode.zerotoheroandroidtdd.UiState

sealed interface LoadResult {

    fun show(updateLiveData: LiveDataWrapper.Update)

    class Success(val data: SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(UiState.ShowData(data.text))
        }

        override fun equals(other: Any?): Boolean {
            return data.text == (other as Success).data.text
        }

        override fun hashCode(): Int {
            return data.hashCode()
        }
    }

    class Error(val noConnection: Boolean) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            if (noConnection) {
                updateLiveData.update(UiState.ShowData("No internet connection"))
            } else {
                updateLiveData.update(UiState.ShowData("Something went wrong"))
            }
        }

        override fun equals(other: Any?): Boolean {
            return noConnection == (other as Error).noConnection
        }

        override fun hashCode(): Int {
            return noConnection.hashCode()
        }
    }
}