package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progressBar) }
    private val textView: TextView by lazy { findViewById(R.id.titleTextView) }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            GlobalScope.launch(IO) {
                runOnUiThread { UiState.Loading.apply(progressBar, button, textView) }
                delay(3000)
                runOnUiThread { UiState.Success.apply(progressBar, button, textView) }
            }
        }
    }

    sealed interface UiState {

        fun apply(progressBar: ProgressBar, button: Button, textView: TextView)

        data object Loading : UiState {
            override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
                progressBar.isVisible = true
                button.isEnabled = false
                textView.isVisible = false
            }

        }

        data object Success : UiState {
            override fun apply(progressBar: ProgressBar, button: Button, textView: TextView) {
                progressBar.isVisible = false
                button.isEnabled = true
                textView.isVisible = true
            }

        }
    }
}