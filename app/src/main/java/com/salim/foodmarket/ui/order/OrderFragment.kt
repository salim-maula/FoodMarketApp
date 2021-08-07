package com.salim.foodmarket.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.salim.foodmarket.R
import com.salim.foodmarket.model.response.transaction.Data
import com.salim.foodmarket.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

    var pastordersList: ArrayList<Data>? = ArrayList()
    var inprogressList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onTransactionSuccess(transactonResponse: TransactionResponse) {
        if (transactonResponse.data.isNullOrEmpty()) {
            include_toolbar.visibility = View.GONE
            ll_tab.visibility = View.GONE
            ll_empty.visibility = View.VISIBLE
        }else{
            for (a in transactonResponse.data.indices) {
                // past order
                if (transactonResponse.data[a].status.equals("ON_DELIVERY", true)
                        || transactonResponse.data[a].status.equals("PENDING", true)) {
                    inprogressList?.add(transactonResponse.data[a])
                    // progress
                } else if (transactonResponse.data[a].status.equals("DELIVERY", true)
                        || transactonResponse.data[a].status.equals("DELIVERED", true)
                        || transactonResponse.data[a].status.equals("CANCELLED", true)
                        || transactonResponse.data[a].status.equals("SUCCESS", true)) {
                    pastordersList?.add(transactonResponse.data[a])
                }
                val sectionsPagerAdapter = SectionPagerAdapter(
                    childFragmentManager
                )
                sectionsPagerAdapter.setData(inprogressList, pastordersList)
                //viewPager!!.offscreenPageLimit = 3
                viewPager.adapter = sectionsPagerAdapter
                tabLayout.setupWithViewPager(viewPager)
            }

        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}