package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.mapToBasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.view.View
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ControllerUnitTest {

    private lateinit var controller: Controller
    private lateinit var model: Basket
    private lateinit var view: View

    @Before
    fun setting() {
        model = mock()
        view = mock()
        controller = Controller(view, model)
    }

    @Test
    fun `whenDisplayItemClicked_itAlreadyExist_shouldUpdateItem`() {
        val item = Item("banana")
        whenever(model.items).thenReturn(listOf(BasketItem("banana")))

        controller.onDisplayItemClicked(item)

        verify(model,times(1)).updateItem(0)
    }

    @Test
    fun `whenDisplayItemClicked_itAlreadyExist_shouldNotifyViewItemUpdated`() {
        val item = Item("banana")
        whenever(model.items).thenReturn(listOf(BasketItem("banana")))

        controller.onDisplayItemClicked(item)

        verify(view, times(1)).onBasketItemUpdated(0)
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldAddItem`() {
        val item = Item("banana")
        val basketItem = item.mapToBasketItem()
        whenever(model.items).thenReturn(emptyList())

        controller.onDisplayItemClicked(item)

        verify(model, times(1)).addItem(basketItem)
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldNotifyViewItemAdded`() {
        val item = Item("banana")
        val basketItem = item.mapToBasketItem()
        `when`(model.items).thenReturn(emptyList())
        `when`(model.addItem(basketItem)).thenAnswer {
            `when`(model.items).thenReturn(listOf(basketItem))
        }

        controller.onDisplayItemClicked(item)

        verify(view, times(1)).onBasketItemAdded(0)
    }
}