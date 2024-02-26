package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val count = Count.Base(step = 2, max = 4)
    private var uiState: UiState = UiState.Base("0")
    private val textView: TextView by lazy { findViewById(R.id.countTextView) }
    private val button: Button by lazy { findViewById(R.id.incrementButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val savedState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState?.getSerializable(KEY, UiState::class.java)
        } else {
            savedInstanceState?.getSerializable(KEY)
        } as? UiState
        savedState?.apply(textView, button)

        button.setOnClickListener {
            uiState = count.increment(textView.text.toString())
            uiState.apply(textView, button)
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
    fun apply(textView: TextView, button: Button)
    data class Base(val text: String) : UiState {

        override fun apply(textView: TextView, button: Button) {
            textView.text = text
        }
    }

    data class Max(val text: String) : UiState {
        override fun apply(textView: TextView, button: Button) {
            textView.text = text
            button.isEnabled = false
        }

    }

}