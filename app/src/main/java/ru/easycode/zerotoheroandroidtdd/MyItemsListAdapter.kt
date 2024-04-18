package ru.easycode.zerotoheroandroidtdd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class MyItemsListAdapter : RecyclerView.Adapter<MyItemsListAdapter.PersonViewHolder>() {

    private var data: MutableList<CharSequence> = mutableListOf()
    fun updateItems(listItems: List<CharSequence>) {
        val itemsDiffUtilCallback = MyItemsListDiffUtilsCallback(data, listItems)
        val itemsDiffResult = DiffUtil.calculateDiff(itemsDiffUtilCallback)

        data = listItems.toMutableList()
        itemsDiffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.element_text_view, parent, false);
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =
        holder.bind(data[position])

    class PersonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val textView: TextView by lazy { view.findViewById(R.id.elementTextView) }

        fun bind(text: CharSequence) {
            textView.text = text
        }

    }

}

class MyItemsListDiffUtilsCallback(
    private val oldList: List<CharSequence>,
    private val newList: List<CharSequence>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}