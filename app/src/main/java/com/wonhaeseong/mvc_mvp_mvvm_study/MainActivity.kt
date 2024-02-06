package com.wonhaeseong.mvc_mvp_mvvm_study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheet = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        val basket = BottomSheetDialog(this)
        basket.setContentView(bottomSheet)
        basket.behavior.skipCollapsed = true

        val basketBtn = findViewById<FloatingActionButton>(R.id.basket_btn)
        basketBtn.setOnClickListener {
            basket.show()
        }
    }

    override fun onBasketUpdated() {

    }
}