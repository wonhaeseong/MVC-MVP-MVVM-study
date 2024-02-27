package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item

class DisplayAdapter(
    private val items: List<Item>,
    private val onItemClick: (Item) -> Unit
) : RecyclerView.Adapter<DisplayAdapter.DisplayViewHolder>() {
    class DisplayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val displayItem: ConstraintLayout
        val displayItemImg: ImageView
        val displayItemName: TextView

        init {
            displayItem = view.findViewById(R.id.display_item)
            displayItemImg = view.findViewById(R.id.display_item_img)
            displayItemName = view.findViewById(R.id.display_item_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayViewHolder {
        return DisplayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.display_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DisplayViewHolder, position: Int) {
        holder.displayItem.setOnClickListener {
            onItemClick(items[position])
        }
        holder.displayItemImg.setImageResource(items[position].imgSrc)
        holder.displayItemName.text = items[position].name
    }

    override fun getItemCount(): Int = items.size
}