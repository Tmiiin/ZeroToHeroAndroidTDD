package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.view.contains
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val textView by lazy { findViewById<TextView>(R.id.titleTextView) }
    private val rootLayout: LinearLayout by lazy { findViewById(R.id.rootLayout) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.getBoolean(KEY_TEXT_REMOVED)?.let {
            if (it) {
                rootLayout.removeView(textView)
            }
        }

        findViewById<Button>(R.id.removeButton).setOnClickListener {
            rootLayout.removeView(textView)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_TEXT_REMOVED, !rootLayout.contains(textView))
        super.onSaveInstanceState(outState)
    }

    companion object {
        private const val KEY_TEXT_REMOVED = "text.removed"
    }
}