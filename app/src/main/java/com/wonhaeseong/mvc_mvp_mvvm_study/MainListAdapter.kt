package com.wonhaeseong.mvc_mvp_mvvm_study

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MainListAdapter(
    context: Context,
    private val items: List<Item>
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int = items.size

    override fun getItem(position: Int) = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: View =
            convertView ?: inflater.inflate(R.layout.main_list_item, parent, false)
        viewHolder.findViewById<ImageView>(R.id.main_item_img).apply {
            setImageResource(items[position].imgSrc)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        viewHolder.findViewById<TextView>(R.id.main_item_name).text = items[position].name
        return viewHolder
    }
}