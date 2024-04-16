package ru.easycode.zerotoheroandroidtdd

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val inputEditText: TextInputEditText by lazy { findViewById(R.id.inputEditText) }
    private val contentLayout: LinearLayout by lazy { findViewById(R.id.contentLayout) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let { savedState ->
            savedState.getStringArrayList(KEY)?.forEach {text ->
                contentLayout.addView(TextViewCreator().createTextView(this, text))
            }
        }

        button.setOnClickListener {
            val text = inputEditText.text.toString()
            val textView = TextViewCreator().createTextView(this, text)
            contentLayout.addView(textView)
                inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val texts = mutableListOf<String>()
        contentLayout.children.map {
            if (it as? TextView != null) {
                texts.add(it.text.toString())
            }
        }
        outState.putStringArrayList(KEY, texts as ArrayList<String>)
    }

    companion object {
        private const val KEY = "key_texts"
    }
}

internal class TextViewCreator {
    fun createTextView(context: Context, text: String): TextView {
        return TextView(context).apply {
            layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            this.text = text
        }
    }
}