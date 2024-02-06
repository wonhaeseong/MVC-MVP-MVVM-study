package com.wonhaeseong.mvc_mvp_mvvm_study

import org.junit.Assert.*
import org.junit.Test

class BasketUnitTest {

    @Test
    fun `whenAddSameItemToBasket_shouldNotIncreaseNumberOfItemTypes`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")

        assertEquals(1, basket.numberOfItemTypes)
    }

    @Test
    fun `whenAddItemsToBasket_shouldIncreaseNumberOfItems`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")
        basket.addItem("apple")

        assertEquals(3, basket.numberOfItems)
    }

    @Test
    fun `whenRemoveItemFromBasket_shouldDecreaseNumberOfItems`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")
        basket.removeItem("banana")

        assertEquals(1, basket.numberOfItems)
    }

    @Test
    fun `whenRemoveItemFromBasket_noMoreItem_shouldNotContainItem`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.removeItem("banana")

        assertFalse(basket.items.contains("banana"))
    }

    @Test
    fun `whenRemoveAllItemsFromBasket_shouldNotContainItem`(){
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")
        basket.addItem("apple")
        basket.addItem("mango")
        basket.removeAllItems()

        assertTrue(basket.items.isEmpty())
    }
}