package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val textView: TextView by lazy { findViewById(R.id.titleTextView) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }
    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val viewModel = MainViewModel(repository = ActualRepository(), liveDataWrapper = ActualLiveDataWrapper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            viewModel.load()
        }
        viewModel.liveData().observe(this) {
            it.apply(progressBar, button, textView)
        }
    }
}

sealed interface UiState {

    fun apply(progressBar: ProgressBar, button: Button, textView: TextView)

    data object ShowProgress : UiState {
        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
            progressBar.isVisible = true
            button.isEnabled = false
            textView.isVisible = false
        }

    }

    data object ShowData : UiState {
        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
            progressBar.isVisible = false
            button.isEnabled = true
            textView.isVisible = true
        }

    }
}