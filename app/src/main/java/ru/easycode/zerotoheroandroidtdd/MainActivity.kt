package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val inputEditText: TextInputEditText by lazy { findViewById(R.id.inputEditText) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter: RVAdapter by lazy { RVAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.restoreState(savedInstanceState)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            val text = inputEditText.text.toString()
            adapter.addItem(text)
            inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter.saveState(outState)
    }

}