package com.upwork.nytimes.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upwork.nytimes.ApiClient.MostPopularData
import com.upwork.nytimes.ApiClient.MostPopularResponse
import com.upwork.nytimes.Base.AppBaseAdapter
import com.upwork.nytimes.databinding.ItemMostPopularCellBinding

class PopularListAdapter : AppBaseAdapter<MostPopularData, ItemMostPopularCellBinding>() {

    override fun getViewBinding(parent: ViewGroup, attachToRoot: Boolean) =
        ItemMostPopularCellBinding.inflate(LayoutInflater.from(parent.context), parent, attachToRoot)

    override fun setClickableView(itemView: View): List<View?> = listOf(binding.divCell)

    override fun onBind(
        viewType: Int,
        view: ItemMostPopularCellBinding,
        position: Int,
        item: MostPopularData,
        payloads: MutableList<Any>?
    ) {
        view.run {
            lblTitle.text = item.title ?: ""
            lblDescription.text = item.abstract ?: ""
            lblDate.text =  item.published_date ?: ""
        }
    }
}