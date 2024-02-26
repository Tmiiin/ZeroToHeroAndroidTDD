package ru.easycode.zerotoheroandroidtdd

import java.lang.IllegalStateException

sealed class Count(private val step: Int) {
    fun increment(number: String): String {
        return (number.toInt() + step).toString()
    }

    class Base(step: Int) : Count(step) {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was -2")
            }
        }
    }
}