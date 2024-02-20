package com.wonhaeseong.mvc_mvp_mvvm_study

import org.junit.Assert.*
import org.junit.Test

class BasketUnitTest {

    @Test
    fun `whenAddSameItemToBasket_shouldNotIncreaseNumberOfItemTypes`() {
        val basket = Basket()

        basket.addItem(Item("banana"))
        basket.addItem(Item("banana"))

        assertEquals(1, basket.numberOfItemTypes)
    }

    @Test
    fun `whenAddItemsToBasket_shouldIncreaseNumberOfItems`() {
        val basket = Basket()

        basket.addItem(Item("banana"))
        basket.addItem(Item("banana"))
        basket.addItem(Item("apple"))

        assertEquals(3, basket.numberOfItems)
    }

    @Test
    fun `whenRemoveItemFromBasket_shouldNotContainItem`() {
        val basket = Basket()

        basket.addItem(Item("banana"))
        basket.addItem(Item("banana"))
        basket.removeItem(BasketItem("banana"))

        assertNull(basket.items.find { it.name == "banana" })
    }

    @Test
    fun `whenRemoveAllItemsFromBasket_shouldNotContainItem`() {
        val basket = Basket()

        basket.addItem(Item("banana"))
        basket.addItem(Item("banana"))
        basket.addItem(Item("apple"))
        basket.addItem(Item("mango"))
        basket.removeAllItems()

        assertTrue(basket.items.isEmpty())
    }
}