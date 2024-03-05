package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem

class BasketAdapter(
    private val basketItems: ArrayList<BasketItem> = arrayListOf(),
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

    fun setItem(updatedItems: List<BasketItem>) {
        val diffCallback = BasketDiffUtilCallback(basketItems, updatedItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        basketItems.run {
            clear()
            addAll(updatedItems)
            diffResult.dispatchUpdatesTo(this@BasketAdapter)
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

class BasketDiffUtilCallback(
    private val old: List<BasketItem>,
    private val new: List<BasketItem>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}