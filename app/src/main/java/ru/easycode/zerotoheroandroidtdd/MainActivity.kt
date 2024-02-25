package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy { findViewById<TextView>(R.id.titleTextView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.getInt(KEY_TEXT_INVISIBLE, View.VISIBLE)?.let {
            textView.visibility = it
        }
        findViewById<Button>(R.id.hideButton).setOnClickListener {
            textView.visibility = View.GONE
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_TEXT_INVISIBLE, textView.visibility)
        super.onSaveInstanceState(outState)
    }
    companion object {
        private const val KEY_TEXT_INVISIBLE = "text.invisible"
    }
}