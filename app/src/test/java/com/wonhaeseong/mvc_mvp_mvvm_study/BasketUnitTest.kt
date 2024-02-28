package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import org.junit.Assert.*
import org.junit.Test

class BasketUnitTest {
    @Test
    fun `whenAddBasketItem_shouldIncreaseNumberOfItems`() {
        val basket = Basket()
        val basketItem = BasketItem("banana")

        basket.addItem(basketItem)

        assertEquals(1, basket.numberOfItems)
    }

    @Test
    fun `whenUpdateBasketItem_shouldIncreaseNumber`() {
        val basket = Basket()
        val basketItem = BasketItem("banana")

        basket.addItem(basketItem)
        basket.updateItem(0)

        assertEquals(2, basketItem.number)
    }

    @Test
    fun `whenUpdateBasketItem_shouldIncreaseNumberOfItems`() {
        val basket = Basket()
        val basketItem = BasketItem("banana")

        basket.addItem(basketItem)
        basket.updateItem(0)

        assertEquals(2, basket.numberOfItems)
    }
    @Test
    fun `whenRemoveBasetItem_shouldNotContainBasetItem`() {
        val basket = Basket()
        val basketItem = BasketItem("banana")
        basket.addItem(basketItem)
        basket.removeItem(0)

        assertEquals(-1, basket.items.indexOf(basketItem))
    }

    @Test
    fun `whenRemoveAllItemsFromBasket_shouldNotContainItem`() {
        val basket = Basket()

        basket.addItem(BasketItem("banana"))
        basket.addItem(BasketItem("apple"))
        basket.addItem(BasketItem("mango"))
        basket.removeAllItems()

        assertTrue(basket.items.isEmpty())
    }
}