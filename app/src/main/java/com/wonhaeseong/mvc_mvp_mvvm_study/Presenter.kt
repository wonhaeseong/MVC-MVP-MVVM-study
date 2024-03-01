package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository
import com.wonhaeseong.mvc_mvp_mvvm_study.view.View

class Presenter(
    private val view: View,
    private val model: Repository
) {
    init {
        view.initView(
            displayItems = model.displayItems,
            basketItems = model.basketItems
        )
    }
    fun onDisplayItemClicked(item: Item) {
        val existingItemIndex = model.basketItems.indexOfFirst { item.name == it.name }
        if (existingItemIndex == -1) {
            model.addItemToBasket(item)
            view.updateBasketOnItemAdded(model.basketItems.lastIndex)
            view.updateNumberOfBasketItemsView(model.numberOfBasketItems.toString())
        } else {
            model.incrementBasketItemQuantity(existingItemIndex)
            view.updateBasketOnItemQuantityIncrement(existingItemIndex)
            view.updateNumberOfBasketItemsView(model.numberOfBasketItems.toString())
        }
    }

    fun onBasketItemRemoveBtnClicked(basketItem: BasketItem) {
        val itemIndex = model.basketItems.indexOf(basketItem)
        if (itemIndex != -1) {
            model.removeBasketItem(itemIndex)
            view.updateBasketOnItemRemoved(itemIndex)
            view.updateNumberOfBasketItemsView(model.numberOfBasketItems.toString())
        }
    }

    fun onBuyBtnClicked(){
        val itemCount = model.basketItems.size
        model.clearBasket()
        view.updateBasketOnBasketCleared(itemCount)
        view.updateNumberOfBasketItemsView(model.numberOfBasketItems.toString())
    }
}