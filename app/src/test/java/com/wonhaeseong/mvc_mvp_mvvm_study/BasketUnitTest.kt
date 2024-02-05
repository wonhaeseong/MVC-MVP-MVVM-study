package com.wonhaeseong.mvc_mvp_mvvm_study

import org.junit.Assert.*
import org.junit.Test

class BasketUnitTest {

    @Test
    fun `whenAddSameItemToBasket_shouldNotIncreaseUniqueItemCount`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")

        assertEquals(1, basket.uniqueItemCount)
    }

    @Test
    fun `whenAddItemsToBasket_shouldIncreaseTotalItemCount`() {
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")
        basket.addItem("apple")

        assertEquals(3, basket.totalItemCount)
    }

    @Test
    fun `whenRemoveItemFromBasket_shouldDecreaseTotalItemCount`(){
        val basket = Basket()

        basket.addItem("banana")
        basket.addItem("banana")
        basket.removeItem("banana")

        assertEquals(1,basket.totalItemCount)
    }

//    @Test
//    fun `whenRemoveItemFromBasket_noMoreItem_shouldNotContainItem`(){
//        val basket = Basket()
//
//        basket.addItem("banana")
//        basket.removeItem("banana")
//
//        assertTrue(basket.items)
//    }
}