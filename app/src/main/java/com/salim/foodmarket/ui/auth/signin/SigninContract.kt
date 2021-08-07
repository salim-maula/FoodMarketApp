package com.salim.foodmarket.ui.auth.signin

import com.salim.foodmarket.base.BasePresenter
import com.salim.foodmarket.base.BaseView
import com.salim.foodmarket.model.response.login.LoginResponse

interface SigninContract {

    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}