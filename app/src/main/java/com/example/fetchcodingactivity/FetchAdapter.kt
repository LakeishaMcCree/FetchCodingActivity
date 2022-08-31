package com.example.fetchcodingactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView

class FetchAdapter : RecyclerView.Adapter<FetchAdapter.ItemHolder>() {
    private var fetchItems: List<FetchItem> = emptyList()

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView

        fun bind(model: FetchItem) {
            val id = view.findViewById<MaterialTextView>(R.id.tvId)
            val listId = view.findViewById<MaterialTextView>(R.id.tvListId)
            val name = view.findViewById<MaterialTextView>(R.id.tvName)

            id.text = model.id.toString()
            listId.text = model.listId.toString()
            name.text = model.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.fetch_item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(fetchItems[position])
    }

    override fun getItemCount(): Int {
        return fetchItems.size
    }

    fun fetchItems(newItems: List<FetchItem>) {
        fetchItems = newItems
    }


}




