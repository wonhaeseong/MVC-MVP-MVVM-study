package com.wonhaeseong.mvc_mvp_mvvm_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View {
    private lateinit var basket: Basket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        basket = Basket()
        val basketView = layoutInflater.inflate(R.layout.basket, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(basketView)
        bottomSheetDialog.behavior.skipCollapsed = true
        val itemListView = findViewById<ListView>(R.id.item_list)
        itemListView.adapter = MainListAdapter(this, listOf()) // TODO: Shop(model)에서 List<Item> 가져오기
        val basketBtn = findViewById<FloatingActionButton>(R.id.basket_btn)
        basketBtn.setOnClickListener {
            bottomSheetDialog.show()
        }
    }

    override fun onBasketUpdated() {

    }
}