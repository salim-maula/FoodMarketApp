package com.salim.foodmarket.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.salim.foodmarket.FoodMarket
import com.salim.foodmarket.MainActivity
import com.salim.foodmarket.R
import com.salim.foodmarket.model.response.login.LoginResponse
import com.salim.foodmarket.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_signin.*

class SigninFragment : Fragment(), SigninContract.View {

    lateinit var presenter: SigninPresenter
    var progressDialog : Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)

        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initDummy()
        initView()

        btnSignup.setOnClickListener{
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request",2)
            startActivity(signup)
        }

        btnSignin.setOnClickListener {

            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan Email"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan Password"
                etPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }
    }

    private fun initDummy() {

        etEmail.setText("huzaifah956@gmail.com")
        etPassword.setText("salim123")

    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout= layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}






















