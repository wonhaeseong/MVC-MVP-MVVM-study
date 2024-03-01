package com.wonhaeseong.mvc_mvp_mvvm_study

import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository
import com.wonhaeseong.mvc_mvp_mvvm_study.view.View
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PresenterUnitTest {

    private lateinit var presenter: Presenter
    private lateinit var model: Repository
    private lateinit var view: View

    @Before
    fun setUp() {
        model = mock()
        view = mock()
        presenter = Presenter(view, model)
    }

    @Test
    fun `whenDisplayItemClicked_itAlreadyExist_shouldUpdateItem`() {
        val item = Item("banana")
        whenever(model.basketItems).thenReturn(listOf(BasketItem("banana")))

        presenter.onDisplayItemClicked(item)

        verify(model, times(1)).incrementBasketItemQuantity(0)
    }

    @Test
    fun `whenDisplayItemClicked_itAlreadyExist_shouldNotifyViewItemUpdated`() {
        val item = Item("banana")
        `when`(model.basketItems).thenReturn(listOf(BasketItem("banana")))
        `when`(model.incrementBasketItemQuantity(0)).thenAnswer {
            `when`(model.numberOfBasketItems).thenReturn(2)
        }

        presenter.onDisplayItemClicked(item)

        verify(view, times(1)).updateBasketOnItemQuantityIncrement(0)
        verify(view, times(1)).updateNumberOfBasketItemsView("2")
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldAddItem`() {
        val item = Item("banana")
        whenever(model.basketItems).thenReturn(emptyList())

        presenter.onDisplayItemClicked(item)

        verify(model, times(1)).addItemToBasket(item)
    }

    @Test
    fun `whenDisplayItemClicked_itDoseNotExist_shouldNotifyViewItemAdded`() {
        val item = Item("banana")
        val basketItem = BasketItem("banana")
        `when`(model.basketItems).thenReturn(emptyList())
        `when`(model.addItemToBasket(item)).thenAnswer {
            `when`(model.basketItems).thenReturn(listOf(basketItem))
            `when`(model.numberOfBasketItems).thenReturn(1)
        }

        presenter.onDisplayItemClicked(item)

        verify(view, times(1)).updateBasketOnItemAdded(0)
        verify(view, times(1)).updateNumberOfBasketItemsView("1")
    }

    @Test
    fun `whenBasketItemRemoveBtnClicked_shouldRemoveItem`() {
        val basketItem = BasketItem("banana")
        whenever(model.basketItems).thenReturn(listOf(basketItem))

        presenter.onBasketItemRemoveBtnClicked(basketItem)

        verify(model, times(1)).removeBasketItem(0)
    }

    @Test
    fun `whenBasketItemRemoveBtnClicked_shouldNotifyViewItemDeleted`() {
        val basketItem = BasketItem("banana")
        `when`(model.basketItems).thenReturn(listOf(basketItem))
        `when`(model.removeBasketItem(0)).thenAnswer {
            `when`(model.numberOfBasketItems).thenReturn(0)
        }

        presenter.onBasketItemRemoveBtnClicked(basketItem)

        verify(view, times(1)).updateBasketOnItemRemoved(0)
        verify(view, times(1)).updateNumberOfBasketItemsView("0")
    }

    @Test
    fun `whenBuyBtnClicked_shouldRemoveItemsAll`() {
        val basketItem = BasketItem("banana")
        whenever(model.basketItems).thenReturn(listOf(basketItem))

        presenter.onBuyBtnClicked()

        verify(model, times(1)).clearBasket()
    }

    @Test
    fun `whenBuyBtnClicked_shouldNotifyViewBasketCleared`() {
        val basketItem1 = BasketItem("banana")
        val basketItem2 = BasketItem("apple")
        `when`(model.basketItems).thenReturn(listOf(basketItem1, basketItem2))
        `when`(model.clearBasket()).thenAnswer {
            `when`(model.numberOfBasketItems).thenReturn(0)
        }

        presenter.onBuyBtnClicked()

        verify(view, times(1)).updateBasketOnBasketCleared(2)
        verify(view, times(1)).updateNumberOfBasketItemsView("0")
    }
}