package com.wonhaeseong.mvc_mvp_mvvm_study.model

class Repository(
    display: Display,
    private val basket: Basket
) {
    val displayItems: List<Item> = display.items
    val basketItems: List<BasketItem> = basket.items
    val numberOfBasketItems: Int
        get() = basket.numberOfItems

    fun addItemToBasket(item: Item) {
        basket.addItem(item.mapToBasketItem())
    }

    fun incrementBasketItemQuantity(index: Int) {
        basket.incrementItemQuantity(index)
    }

    fun removeBasketItem(index: Int) {
        basket.removeItem(index)
    }

    fun clearBasket() {
        basket.removeAllItems()
    }
}