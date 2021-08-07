package com.salim.foodmarket.ui.auth.signup

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.salim.foodmarket.FoodMarket
import com.salim.foodmarket.R
import com.salim.foodmarket.model.request.RegisterRequest
import com.salim.foodmarket.model.response.login.LoginResponse
import com.salim.foodmarket.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_signup_address.*


class SignupAddressFragment : Fragment(), SignupContract.View {

    private lateinit var data: RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog: Dialog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()

    }

    private fun initListener() {

        btnSignUpNow.setOnClickListener{

            var phone = etPhoneNumber.text.toString()
            var address = etAddress.text.toString()
            var houseNumber = etHouseNumber.text.toString()
            var city = etCity.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            if (phone.isNullOrEmpty()) {
                etPhoneNumber.error = "Silahkan masukkan nomor handphone"
                etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                etAddress.error = "Silahkan masukkan alamat"
                etAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                etHouseNumber.error = "Silahkan masukkan no rumah"
                etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                etCity.error = "Silahkan masukkan kota"
                etCity.requestFocus()
//            } else if (data.filePath == null) {
//                presenter.submitRegister(data, view)
            } else {
//                presenter.submitRegister(data, it)
                presenter.submitPhotoRegister(data.filePath!!, it)
            }

        }
    }

    private fun initDummy() {
        etPhoneNumber.setText("08123456711")
        etAddress.setText("Jalan Jenderal Gajah")
        etHouseNumber.setText("155")
        etCity.setText("Depok")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if(data.filePath == null) {
            Navigation.findNavController(view).navigate(R.id.action_signup_success)
            (activity as AuthActivity).toolbarSignUpSucces()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSucces()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun initView(){
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

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