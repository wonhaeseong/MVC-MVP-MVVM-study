package com.wonhaeseong.mvc_mvp_mvvm_study.view

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item

interface View {
    fun initView(displayItems: List<Item>, basketItems: List<BasketItem>)
    fun updateBasketOnItemAdded(index: Int)
    fun updateBasketOnItemRemoved(index: Int)
    fun updateBasketOnItemQuantityIncrement(index: Int)
    fun updateBasketOnBasketCleared(itemCount: Int)
    fun updateNumberOfBasketItemsView(numberOfBasketItems: String)
}