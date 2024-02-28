package com.wonhaeseong.mvc_mvp_mvvm_study.view

interface View {
    fun onBasketItemAdded(index: Int)
    fun onBasketItemDeleted(index: Int)
    fun onBasketItemUpdated(index: Int)
    fun onBasketCleared(itemCount: Int)
}