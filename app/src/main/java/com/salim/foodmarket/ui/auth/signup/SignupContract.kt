package com.salim.foodmarket.ui.auth.signup

import android.net.Uri
import com.salim.foodmarket.base.BasePresenter
import com.salim.foodmarket.base.BaseView
import com.salim.foodmarket.model.request.RegisterRequest
import com.salim.foodmarket.model.response.login.LoginResponse

interface SignupContract {

    interface View : BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse, view:android.view.View)
        fun onRegisterPhotoSuccess(view:android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignupContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest, view:android.view.View)
        fun submitPhotoRegister(filePath: Uri, view:android.view.View)
    }
}