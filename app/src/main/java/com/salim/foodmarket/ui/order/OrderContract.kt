package com.salim.foodmarket.ui.order

import com.salim.foodmarket.base.BasePresenter
import com.salim.foodmarket.base.BaseView
import com.salim.foodmarket.model.response.home.HomeResponse
import com.salim.foodmarket.model.response.login.LoginResponse
import com.salim.foodmarket.model.response.transaction.TransactionResponse

interface OrderContract {

    interface View : BaseView {
        fun onTransactionSuccess(transactonResponse: TransactionResponse)
        fun onTransactionFailed(message: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}