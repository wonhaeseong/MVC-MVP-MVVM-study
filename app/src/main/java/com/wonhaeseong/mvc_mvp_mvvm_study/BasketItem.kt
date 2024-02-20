package com.wonhaeseong.mvc_mvp_mvvm_study

data class BasketItem(val name: String, var number: Int = 1) {
    fun add() {
        number++
    }
}
