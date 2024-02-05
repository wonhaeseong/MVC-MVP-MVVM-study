package com.wonhaeseong.mvc_mvp_mvvm_study

class Basket {
    private val _items: MutableMap<String, Int> = mutableMapOf()

    var uniqueItemCount: Int = 0
        get() = _items.size
        private set

    var totalItemCount: Int = 0
        get() = _items.values.sumOf { it }
        private set

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
}