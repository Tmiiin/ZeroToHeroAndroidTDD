package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val textView: TextView by lazy { findViewById(R.id.titleTextView)}
    private val button:Button by lazy { findViewById(R.id.actionButton) }
    private val inputEditText: TextInputEditText by lazy { findViewById(R.id.inputEditText) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = inputEditText.text.toString()
            inputEditText.text?.clear()
        }
        inputEditText.doOnTextChanged { text, _, _, _ ->
            text ?: return@doOnTextChanged
            button.isEnabled = text.length >= 3
        }
    }
}