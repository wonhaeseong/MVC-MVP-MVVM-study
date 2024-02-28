package com.wonhaeseong.mvc_mvp_mvvm_study

import android.util.Log
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.mapToBasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.view.View

class Controller(
    private val view: View,
    private val model: Basket
) {
    fun onDisplayItemClicked(item: Item) {
        val existingItemIndex = model.items.indexOfFirst { item.name == it.name }
        if (existingItemIndex == -1) {
            model.addItem(item.mapToBasketItem())
            view.onBasketItemAdded(model.items.lastIndex)
        } else {
            model.updateItem(existingItemIndex)
            view.onBasketItemUpdated(existingItemIndex)
        }
    }

    fun onBasketItemRemoveBtnClicked(basketItem: BasketItem) {
        val itemIndex = model.items.indexOf(basketItem)
        if (itemIndex != -1) {
            model.removeItem(itemIndex)
            view.onBasketItemDeleted(itemIndex)
        }
    }

    fun onBuyBtnClicked(){
        val itemCount = model.items.size
        model.removeAllItems()
        view.onBasketCleared(itemCount)
    }
}