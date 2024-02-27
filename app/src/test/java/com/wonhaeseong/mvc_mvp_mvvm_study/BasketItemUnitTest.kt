package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import org.junit.Assert.*
import org.junit.Test

class BasketItemUnitTest {
    @Test
    fun `whenAddItem_shouldIncreaseNumber`() {
        val basketItem = BasketItem("banana", number = 1)
        basketItem.add()
        assertEquals(2, basketItem.number)
    }
}