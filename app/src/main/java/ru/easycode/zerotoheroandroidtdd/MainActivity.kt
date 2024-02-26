package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var count: Count = Count.Base(step = 2)

    private val textView: TextView by lazy { findViewById(R.id.countTextView) }
    private val button: Button by lazy { findViewById(R.id.incrementButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            textView.text = count.increment(textView.text.toString())
        }
    }

}
