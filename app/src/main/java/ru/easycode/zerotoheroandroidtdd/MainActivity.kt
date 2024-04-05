package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val count = Count.Base(step = 2, max = 4, min = 0)
    private var uiState: UiState = UiState.Base("0")
    private val textView: TextView by lazy { findViewById(R.id.countTextView) }
    private val incrementButton: Button by lazy { findViewById(R.id.incrementButton) }
    private val decrementButton: Button by lazy { findViewById(R.id.decrementButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState?.getSerializable(KEY, UiState::class.java)
        } else {
            savedInstanceState?.getSerializable(KEY)
        } as? UiState
        savedState?.apply(textView, incrementButton, decrementButton)

        incrementButton.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, incrementButton, decrementButton)
        }
        decrementButton.setOnClickListener {
            uiState = count.decrement(textView.text.toString())
            uiState.apply(textView, incrementButton, decrementButton)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY, uiState)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val KEY = "state"
    }
}

sealed interface UiState : Serializable {
    fun apply(textView: TextView,  incrementButton: Button, decrementButton: Button)

    data class Base(val text: String) : UiState {

        override fun apply(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            decrementButton.isEnabled = true
            incrementButton.isEnabled = true
        }
    }

    data class Max(val text: String) : UiState {
        override fun apply(textView: TextView,  incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = false
            decrementButton.isEnabled = true
        }

    }

    data class Min(val text: String): UiState {
        override fun apply(textView: TextView, incrementButton: Button, decrementButton: Button) {
            textView.text = text
            incrementButton.isEnabled = true
            decrementButton.isEnabled = false
        }
    }

}