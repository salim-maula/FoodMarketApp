package com.salim.foodmarket.ui.home

import com.salim.foodmarket.base.BasePresenter
import com.salim.foodmarket.base.BaseView
import com.salim.foodmarket.model.response.home.HomeResponse
import com.salim.foodmarket.model.response.login.LoginResponse

interface HomeContract {

    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}