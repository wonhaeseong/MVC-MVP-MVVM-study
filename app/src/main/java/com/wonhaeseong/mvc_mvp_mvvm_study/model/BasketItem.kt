package com.wonhaeseong.mvc_mvp_mvvm_study.model

import androidx.annotation.DrawableRes
import com.wonhaeseong.mvc_mvp_mvvm_study.R

data class BasketItem(
    val name: String,
    @DrawableRes val imgSrc: Int = R.drawable.noimageavailable,
    var number: Int = 1
) {
    fun add() {
        number++
    }
}
