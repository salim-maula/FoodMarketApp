package com.salim.foodmarket.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.salim.foodmarket.model.response.transaction.Data
import com.salim.foodmarket.ui.order.inprogress.InprogressFragment
import com.salim.foodmarket.ui.order.pastorders.PastordersFragment

class SectionPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    var inprogressList: ArrayList<Data>? = ArrayList()
    var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun getItem(position: Int): Fragment {

        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastordersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastOrdersList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> null
        }
    }
    fun setData(inprogressListParms: ArrayList<Data>?, pastOrdersListParms: ArrayList<Data>?) {
        inprogressList = inprogressListParms
        pastOrdersList = pastOrdersListParms
    }
}