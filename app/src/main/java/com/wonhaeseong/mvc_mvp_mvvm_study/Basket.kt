package com.wonhaeseong.mvc_mvp_mvvm_study

class Basket {
    private val _items: MutableMap<String, Int> = mutableMapOf()
    val items: Map<String, Int>
        get() = _items

    val numberOfItemTypes: Int
        get() = _items.size

    val numberOfItems: Int
        get() = _items.values.sumOf { it }

    fun addItem(item: String) {
        if (_items.containsKey(item)) {
            _items[item] = _items[item]!! + 1
        } else {
            _items[item] = 1
        }
    }

    fun removeItem(item: String) {
        if (_items.containsKey(item)) {
            _items[item] = _items[item]!! - 1
            if (_items[item] == 0) {
                _items.remove(item)
            }
        }
    }

    fun removeAllItems() {
        _items.clear()
    }
}