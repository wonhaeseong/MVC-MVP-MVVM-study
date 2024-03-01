package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wonhaeseong.mvc_mvp_mvvm_study.Presenter
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.BasketItem
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Display
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Item
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository

class MainActivity : AppCompatActivity(), View {
    private lateinit var presenter: Presenter
    private lateinit var basketAdapter: BasketAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var numberOfItemsView: TextView
    private lateinit var buyBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = Presenter(this, Repository(Display(), Basket()))
    }

    override fun initView(displayItems: List<Item>, basketItems: List<BasketItem>) {
        initBasketView(basketItems)
        initBasketBtn()
        initDisplayView(displayItems)
    }

    private fun initBasketView(basketItems: List<BasketItem>) {
        val basketView = layoutInflater.inflate(R.layout.basket, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(basketView)

        basketAdapter = BasketAdapter(basketItems) {
            presenter.onBasketItemRemoveBtnClicked(it)
        }
        val basketItemsView = basketView.findViewById<RecyclerView>(R.id.basket_items)
        basketItemsView.adapter = basketAdapter

        numberOfItemsView = basketView.findViewById(R.id.number_of_items)
        buyBtn = basketView.findViewById(R.id.buy_btn)
        buyBtn.setOnClickListener {
            presenter.onBuyBtnClicked()
        }
    }

    private fun initDisplayView(items: List<Item>) {
        val displayView = findViewById<RecyclerView>(R.id.display)
        displayView.adapter = DisplayAdapter(items) { item ->
            presenter.onDisplayItemClicked(item)
        }
    }

    private fun initBasketBtn() {
        val basketBtn = findViewById<FloatingActionButton>(R.id.basket_btn)
        basketBtn.setOnClickListener {
            bottomSheetDialog.show()
        }
    }


    override fun updateBasketOnItemAdded(index: Int) {
        basketAdapter.notifyItemInserted(index)
    }

    override fun updateBasketOnItemRemoved(index: Int) {
        basketAdapter.notifyItemRemoved(index)
    }

    override fun updateBasketOnItemQuantityIncrement(index: Int) {
        basketAdapter.notifyItemChanged(index)
    }

    override fun updateBasketOnBasketCleared(itemCount: Int) {
        basketAdapter.notifyItemRangeRemoved(0, itemCount)
    }

    override fun updateNumberOfBasketItemsView(numberOfBasketItems: String) {
        numberOfItemsView.text = numberOfBasketItems
    }
}