package com.salim.foodmarket.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salim.foodmarket.R
import com.salim.foodmarket.model.dummy.HomeVerticalModel
import com.salim.foodmarket.model.response.home.Data
import com.salim.foodmarket.ui.detail.DetailActivity
import com.salim.foodmarket.ui.home.newtaste.HomeNewtasteAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeRecommendedFragment : Fragment(), HomeNewtasteAdapter.ItemAdapterCallback {

    private var recomendedList : ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_recommended, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recomendedList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        var adapter = HomeNewtasteAdapter(recomendedList!!, this)
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

//    fun initDataDummy (){
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Ayam Bakar","10000","",5f))
//        foodList.add(HomeVerticalModel("Burger Tamayo","20000","",4f))
//        foodList.add(HomeVerticalModel("Bakhwan Cihuy","30000","",4.5f))
//    }

    override fun onClick(v: View?, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data",data)
        startActivity(detail)
    }

}























