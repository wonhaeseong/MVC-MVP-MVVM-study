package com.wonhaeseong.mvc_mvp_mvvm_study

class Basket {
    private var _items: MutableList<BasketItem> = mutableListOf()
    val items: List<BasketItem>
        get() = _items

    val numberOfItemTypes: Int
        get() = _items.size

    val numberOfItems: Int
        get() = _items.sumOf { it.number }

    fun addItem(item: Item) {
        _items.find { it.name == item.name }?.add() ?: _items.add(BasketItem(item.name))
    }

    fun removeItem(item: BasketItem) {
        _items.remove(_items.find { it.name == item.name })
    }

    fun removeAllItems() {
        _items.clear()
    }
}