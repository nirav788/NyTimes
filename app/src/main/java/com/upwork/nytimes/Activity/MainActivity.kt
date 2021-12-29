package com.upwork.nytimes.Activity

import com.upwork.nytimes.Adapters.PopularListAdapter
import com.upwork.nytimes.ApiClient.FaultData
import com.upwork.nytimes.Base.AppBaseActivity
import com.upwork.nytimes.databinding.ActivityMainBinding
import org.jetbrains.anko.toast

class MainActivity : AppBaseActivity<ActivityMainBinding>() {

    lateinit var popularListAdapter: PopularListAdapter

    override fun setViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        binding.rvItemList.apply {
            popularListAdapter = PopularListAdapter()
            adapter = popularListAdapter.also {
                it.addAll(arrayListOf())
            }
        }
    }

    override fun initOnClick() {
        popularListAdapter.setItemClickListener { view, position, data ->
            toast(data.title ?: "")
        }

        getPopularList()
    }

   private fun getPopularList() {
        val prm = HashMap<String, Any>()
        prm.put("api-key", "qkpGHGHCaQe6OJxMP8x8X3chdw7Vuvys")
        callApi(api.getMostPopularList(prm), true) { data ->
            if (data.status.equals("OK", true)) {
                popularListAdapter.addAll(data.results ?: arrayListOf())
            } else {
                toast((data.fault ?: FaultData()).faultstring ?: "")
            }
        }
    }

}