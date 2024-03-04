package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository
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
    private lateinit var model: Repository
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
        whenever(model.basketItems).thenReturn(listOf(BasketItem("banana")))

        controller.onDisplayItemClicked(item)

        verify(model,times(1)).incrementBasketItemQuantity(0)
    }

    @Test
    fun `whenDisplayItemClicked_itAlreadyExist_shouldNotifyViewItemUpdated`() {
        val item = Item("banana")
        whenever(model.basketItems).thenReturn(listOf(BasketItem("banana")))

        controller.onDisplayItemClicked(item)

        verify(view, times(1)).onBasketItemUpdated(0)
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldAddItem`() {
        val item = Item("banana")
        whenever(model.basketItems).thenReturn(emptyList())

        controller.onDisplayItemClicked(item)

        verify(model, times(1)).addItemToBasket(item)
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldNotifyViewItemAdded`() {
        val item = Item("banana")
        val basketItem = BasketItem("banana")
        `when`(model.basketItems).thenReturn(emptyList())
        `when`(model.addItemToBasket(item)).thenAnswer {
            `when`(model.basketItems).thenReturn(listOf(basketItem))
        }

        controller.onDisplayItemClicked(item)

        verify(view, times(1)).onBasketItemAdded(0)
    }

    @Test
    fun `whenBasketItemRemoveBtnClicked_shouldRemoveItem`(){
        val item = Item("banana")
        val basketItem = item.mapToBasketItem()
        whenever(model.basketItems).thenReturn(listOf(basketItem))

        controller.onBasketItemRemoveBtnClicked(basketItem)

        verify(model, times(1)).removeBasketItem(0)
    }

    @Test
    fun `whenBasketItemRemoveBtnClicked_shouldNotifyViewItemDeleted`(){
        val item = Item("banana")
        val basketItem = item.mapToBasketItem()
        whenever(model.basketItems).thenReturn(listOf(basketItem))

        controller.onBasketItemRemoveBtnClicked(basketItem)

        verify(view, times(1)).onBasketItemDeleted(0)
    }
    @Test
    fun `whenBuyBtnClicked_shouldRemoveItemsAll`(){
        val basketItem = BasketItem("banana")
        whenever(model.basketItems).thenReturn(listOf(basketItem))

        controller.onBuyBtnClicked()

        verify(model, times(1)).clearBasket()
    }
    @Test
    fun `whenBuyBtnClicked_shouldNotifyViewBasketCleared`(){
        val basketItem1 = BasketItem("banana")
        val basketItem2 = BasketItem("apple")
        whenever(model.basketItems).thenReturn(listOf(basketItem1,basketItem2))

        controller.onBuyBtnClicked()

        verify(view, times(1)).onBasketCleared(2)
    }
}