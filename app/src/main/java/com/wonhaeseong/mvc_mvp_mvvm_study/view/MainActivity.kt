package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wonhaeseong.mvc_mvp_mvvm_study.Controller
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Display

class MainActivity : AppCompatActivity(), View {
    private lateinit var basket: Basket
    private lateinit var display: Display
    private lateinit var controller: Controller
    private lateinit var basketAdapter: BasketAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var numberOfItemsView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        basket = Basket()
        display = Display()
        controller = Controller(this, basket)
        initBasketView()
        initBasketBtn()
        initDisplayView()
    }

    private fun initBasketView() {
        val basketView = layoutInflater.inflate(R.layout.basket, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(basketView)

        basketAdapter = BasketAdapter(basket.items) {
            controller.onBasketItemRemoveBtnClicked(it)
        }
        val basketItemsView = basketView.findViewById<RecyclerView>(R.id.basket_items)
        basketItemsView.adapter = basketAdapter

        numberOfItemsView = basketView.findViewById(R.id.number_of_items)
    }

    private fun initDisplayView() {
        val displayView = findViewById<RecyclerView>(R.id.display)
        displayView.adapter = DisplayAdapter(display.items) { item ->
            controller.onDisplayItemClicked(item)
        }
    }

    private fun initBasketBtn() {
        val basketBtn = findViewById<FloatingActionButton>(R.id.basket_btn)
        basketBtn.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    override fun onBasketItemAdded(index: Int) {
        basketAdapter.notifyItemInserted(index)
        numberOfItemsView.text = basket.numberOfItems.toString()
    }

    override fun onBasketItemDeleted(index: Int) {
        basketAdapter.notifyItemRemoved(index)
        numberOfItemsView.text = basket.numberOfItems.toString()
    }

    override fun onBasketItemUpdated(index: Int) {
        basketAdapter.notifyItemChanged(index)
        numberOfItemsView.text = basket.numberOfItems.toString()
    }
}