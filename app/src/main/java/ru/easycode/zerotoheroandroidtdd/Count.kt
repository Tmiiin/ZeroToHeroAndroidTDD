package ru.easycode.zerotoheroandroidtdd

sealed interface Count {
    fun increment(number: String): UiState

    class Base(val step: Int, val max: Int) : Count {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was -2")
            }
            if (max <= 0) {
                throw IllegalStateException("max should be positive, but was -2")
            }
            if (max < step) {
                throw IllegalStateException("max should be more than step")
            }
        }

        override fun increment(number: String): UiState {
            val incrementedValue = number.toInt() + step
            return when (incrementedValue + step <= max) {
                true -> UiState.Base(incrementedValue.toString())
                false -> UiState.Max(incrementedValue.toString())
            }
        }

    }
}