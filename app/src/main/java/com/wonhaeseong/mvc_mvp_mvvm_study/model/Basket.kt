package com.wonhaeseong.mvc_mvp_mvvm_study.model

class Basket {
    private val _items: MutableList<BasketItem> = mutableListOf()
    val items: List<BasketItem>
        get() = _items
    val numberOfItems: Int
        get() = _items.sumOf { it.number }

    fun addItem(basketItem: BasketItem) {
        _items.add(basketItem)
    }

    fun updateItem(index: Int) {
        _items[index].add()
    }

    fun removeItem(index: Int) {
        _items.removeAt(index)
    }

    fun removeAllItems() {
        _items.clear()
    }
}