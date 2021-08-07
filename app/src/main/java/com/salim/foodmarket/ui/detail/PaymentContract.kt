package com.salim.foodmarket.ui.detail

import android.view.View
import com.salim.foodmarket.base.BasePresenter
import com.salim.foodmarket.base.BaseView
import com.salim.foodmarket.model.response.checkout.CheckoutResponse
import com.salim.foodmarket.model.response.home.HomeResponse
import com.salim.foodmarket.model.response.login.LoginResponse

interface PaymentContract {

    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(foodId:String, userId : String, quantity : String, total : String, view: android.view.View)
    }
}