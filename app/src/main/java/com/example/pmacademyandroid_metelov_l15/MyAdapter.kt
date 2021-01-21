package com.example.pmacademyandroid_metelov_l15

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private var items: MutableList<SpannableString> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_view_holder, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(fullName: SpannableString) {
        items.add(fullName)
        notifyItemInserted(items.size - 1)
    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(text: SpannableString) {
        itemView.findViewById<TextView>(R.id.tvFullname).text = text
    }
}