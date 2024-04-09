package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val textView: TextView by lazy { findViewById(R.id.titleTextView) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }
    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory()
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            viewModel.load()
        }
        savedInstanceState?.let {
            viewModel.restore(BundleWrapper.Base(it))
        }
        viewModel.liveData().observe(this) {
            it.apply(progressBar, button, textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.save(BundleWrapper.Base(outState))
        super.onSaveInstanceState(outState)
    }
}

sealed interface UiState : Serializable {

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

    data object Initial : UiState {

        override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
            progressBar.isVisible = false
            textView.isVisible = false
            button.isEnabled = true
        }
    }
}