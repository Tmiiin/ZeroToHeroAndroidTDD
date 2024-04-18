package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.actionButton) }
    private val inputEditText: TextInputEditText by lazy { findViewById(R.id.inputEditText) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val adapter: MyItemsListAdapter by lazy { MyItemsListAdapter() }
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory()
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let { viewModel.restore(BundleWrapper.Base(it)) }
        recyclerView.adapter = adapter
        viewModel.liveData().observe(this) {
            adapter.updateItems(it)
        }
        button.setOnClickListener {
            val text = inputEditText.text.toString()
            viewModel.add(text)
            inputEditText.text?.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

}