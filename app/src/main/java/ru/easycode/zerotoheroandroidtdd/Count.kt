package ru.easycode.zerotoheroandroidtdd

sealed interface Count {
    fun increment(number: String): UiState
    fun decrement(number: String): UiState

    fun initial(number: String): UiState

    class Base(val step: Int, val max: Int, val min: Int) : Count {

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
            if (max <= min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun increment(number: String): UiState {
            val incrementedValue = number.toInt() + step
            return when (incrementedValue + step <= max) {
                true -> UiState.Base(incrementedValue.toString())
                false -> UiState.Max(incrementedValue.toString())
            }
        }

        override fun decrement(number: String): UiState {
            val decrementedValue = number.toInt() - step
            return when (decrementedValue - step >= min) {
                true -> UiState.Base(decrementedValue.toString())
                false -> UiState.Min(decrementedValue.toString())
            }
        }

        override fun initial(number: String): UiState {
            val numberInt = number.toInt()
            return when {
                numberInt + step > max -> UiState.Max(number)
                numberInt - step < min -> UiState.Min(number)
                else -> UiState.Base(number)
            }
        }

    }
}