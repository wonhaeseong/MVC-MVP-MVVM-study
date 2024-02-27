package com.wonhaeseong.mvc_mvp_mvvm_study.model

import com.wonhaeseong.mvc_mvp_mvvm_study.R

class Display {
    val items: List<Item> =
        listOf(
            Item("Apple", R.drawable.apple),
            Item("Banana", R.drawable.banana),
            Item("Mango", R.drawable.mango),
            Item("Strawberry", R.drawable.strawberry),
            Item("Watermelon", R.drawable.watermelon),
        )
}