package com.wonhaeseong.mvc_mvp_mvvm_study

import androidx.annotation.DrawableRes

data class Item(
    val name: String,
    @DrawableRes val imgSrc: Int = R.drawable.noimageavailable
)
