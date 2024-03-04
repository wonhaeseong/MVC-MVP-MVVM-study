package com.wonhaeseong.mvc_mvp_mvvm_study.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.wonhaeseong.mvc_mvp_mvvm_study.Controller
import com.wonhaeseong.mvc_mvp_mvvm_study.R
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Basket
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Display
import com.wonhaeseong.mvc_mvp_mvvm_study.model.Repository

class MainActivity : AppCompatActivity(), View {
    private lateinit var model: Repository
    private lateinit var controller: Controller
    private lateinit var basketAdapter: BasketAdapter
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var numberOfItemsView: TextView
    private lateinit var buyBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = Repository(Display(),Basket())
        controller = Controller(this, model)
        initBasketView()
        initBasketBtn()
        initDisplayView()
    }

    private fun initBasketView() {
        val basketView = layoutInflater.inflate(R.layout.basket, null)
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(basketView)

        basketAdapter = BasketAdapter(model.basketItems) {
            controller.onBasketItemRemoveBtnClicked(it)
        }
        val basketItemsView = basketView.findViewById<RecyclerView>(R.id.basket_items)
        basketItemsView.adapter = basketAdapter

        numberOfItemsView = basketView.findViewById(R.id.number_of_items)
        buyBtn = basketView.findViewById(R.id.buy_btn)
        buyBtn.setOnClickListener {
            controller.onBuyBtnClicked()
        }
    }

    private fun initDisplayView() {
        val displayView = findViewById<RecyclerView>(R.id.display)
        displayView.adapter = DisplayAdapter(model.displayItems) { item ->
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
        updateNumberOfBasketItemsView()
    }

    override fun onBasketItemDeleted(index: Int) {
        basketAdapter.notifyItemRemoved(index)
        updateNumberOfBasketItemsView()
    }

    override fun onBasketItemUpdated(index: Int) {
        basketAdapter.notifyItemChanged(index)
        updateNumberOfBasketItemsView()
    }

    override fun onBasketCleared(itemCount: Int) {
        basketAdapter.notifyItemRangeRemoved(0, itemCount)
        updateNumberOfBasketItemsView()
    }

    private fun updateNumberOfBasketItemsView(){
        numberOfItemsView.text = model.numberOfBasketItems.toString()
    }
}