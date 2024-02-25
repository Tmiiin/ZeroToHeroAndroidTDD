package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: State = State.Initial

    private val textView: TextView by lazy { findViewById(R.id.titleTextView) }
    private val rootLayout: LinearLayout by lazy { findViewById(R.id.rootLayout) }
    private val button: Button by lazy { findViewById(R.id.removeButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            state = State.Removed
            state.apply(rootLayout, textView, button)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY_STATE, state)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY_STATE, State::class.java) as State
        } else {
            savedInstanceState.getSerializable(KEY_STATE) as State
        }
        state.apply(rootLayout, textView, button)
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private const val KEY_STATE = "text.removed"
    }
}

sealed interface State : Serializable {

    fun apply(linearLayout: LinearLayout, textView: TextView, button: Button)

    data object Initial : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) = Unit
    }

    data object Removed : State {
        override fun apply(linearLayout: LinearLayout, textView: TextView, button: Button) {
            linearLayout.removeView(textView)
            button.isEnabled = false
        }
    }
}
