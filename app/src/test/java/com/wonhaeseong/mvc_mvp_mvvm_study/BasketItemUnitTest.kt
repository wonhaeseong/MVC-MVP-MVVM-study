package com.wonhaeseong.mvc_mvp_mvvm_study

import org.junit.Assert.*
import org.junit.Test

class BasketItemUnitTest {
    @Test
    fun `whenAddItem_shouldIncreaseNumber`() {
        val basketItem = BasketItem("banana", 1)
        basketItem.add()
        assertEquals(2, basketItem.number)
    }
}