package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter : RecyclerView.Adapter<RVAdapter.PersonViewHolder>() {

    private var data: MutableList<String> = mutableListOf()

    fun addItem(item: String) {
        data.add(item)
        notifyItemChanged(data.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_text_view, parent, false);
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
        holder.bind(data[position])

    fun saveState(outState: Bundle) {
        outState.putStringArrayList(KEY, ArrayList(data))
    }

    fun restoreState(savedInstanceState: Bundle?) {
        savedInstanceState?.getStringArrayList(KEY)?.let {
            data = it.toMutableList()
            notifyItemRangeInserted(0, it.size)
        }
    }

    companion object {
        private const val KEY = "data.key"
    }

    class PersonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView by lazy { view.findViewById(R.id.elementTextView) }

        fun bind(text: String) {
            textView.text = text
            textView.freezesText = true
        }

    }

}