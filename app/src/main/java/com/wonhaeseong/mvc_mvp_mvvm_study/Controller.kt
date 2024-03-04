package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository
import com.wonhaeseong.mvc_mvp_mvvm_study.view.View

class Controller(
    private val view: View,
    private val model: Repository
) {
    fun onDisplayItemClicked(item: Item) {
        val existingItemIndex = model.basketItems.indexOfFirst { item.name == it.name }
        if (existingItemIndex == -1) {
            model.addItemToBasket(item)
            view.onBasketItemAdded(model.basketItems.lastIndex)
        } else {
            model.incrementBasketItemQuantity(existingItemIndex)
            view.onBasketItemUpdated(existingItemIndex)
        }
    }

    fun onBasketItemRemoveBtnClicked(basketItem: BasketItem) {
        val itemIndex = model.basketItems.indexOf(basketItem)
        if (itemIndex != -1) {
            model.removeBasketItem(itemIndex)
            view.onBasketItemDeleted(itemIndex)
        }
    }

    fun onBuyBtnClicked(){
        val itemCount = model.basketItems.size
        model.clearBasket()
        view.onBasketCleared(itemCount)
    }
}