package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem

class BasketAdapter(
    private val basketItems: List<BasketItem>,
    private val onDeleteBtnClick: (BasketItem) -> Unit
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(view: View) : ViewHolder(view) {
        val basketItemImg: ImageView
        val basketItemName: TextView
        val basketItemNum: TextView
        val deleteBtn: ImageButton

        init {
            basketItemImg = view.findViewById(R.id.basket_item_img)
            basketItemName = view.findViewById(R.id.basket_item_name)
            basketItemNum = view.findViewById(R.id.basket_item_num)
            deleteBtn = view.findViewById(R.id.delete_btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.basketItemImg.setImageResource(basketItems[position].imgSrc)
        holder.basketItemName.text = basketItems[position].name
        holder.basketItemNum.text = basketItems[position].number.toString()
        holder.deleteBtn.setOnClickListener {
            onDeleteBtnClick(basketItems[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = basketItems.size
}