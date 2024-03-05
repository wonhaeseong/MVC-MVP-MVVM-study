package com.wonhaeseong.mvc_mvp_mvvm_study.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository
class MainViewModel(
    private val model: Repository
) : ViewModel() {
    val displayItems: List<Item> = model.displayItems

    private val _basketLiveData: MutableLiveData<List<BasketItem>> by lazy {
        MutableLiveData<List<BasketItem>>(model.basketItems)
    }
    val basketLiveData: LiveData<List<BasketItem>>
        get() = _basketLiveData

    private val _numberOfItemsLiveData: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(model.numberOfBasketItems)
    }
    val numberOfItemsLiveData: LiveData<Int>
        get() = _numberOfItemsLiveData

    fun addItemToBasket(item: Item) {
        val existingItemIndex = model.basketItems.indexOfFirst { item.name == it.name }
        if (existingItemIndex == -1) {
            model.addItemToBasket(item)
        } else {
            model.incrementBasketItemQuantity(existingItemIndex)
        }
        _basketLiveData.value = model.basketItems
        _numberOfItemsLiveData.value = model.numberOfBasketItems
    }

    fun removeBasketItem(basketItem: BasketItem) {
        val itemIndex = model.basketItems.indexOf(basketItem)
        if (itemIndex != -1) {
            model.removeBasketItem(itemIndex)
            _basketLiveData.value = model.basketItems
            _numberOfItemsLiveData.value = model.numberOfBasketItems
        }
    }

    fun buy() {
        model.clearBasket()
        _basketLiveData.value = model.basketItems
        _numberOfItemsLiveData.value = model.numberOfBasketItems
    }
}