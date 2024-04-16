package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val inputView: TextInputEditText by lazy { findViewById(R.id.inputEditText) }
    private val textView: TextView by lazy { findViewById(R.id.titleTextView) }
    private val button: Button by lazy { findViewById(R.id.actionButton) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = inputView.text.toString()
            inputView.text?.clear()
        }
    }
}