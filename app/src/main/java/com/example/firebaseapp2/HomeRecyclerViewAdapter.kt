package com.example.firebaseapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapp2.databinding.ItemViewBinding

class HomeRecyclerViewAdapter(val dataList: MutableList<Email>) : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewViewHolder>() {

    inner class HomeRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemViewBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return HomeRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            emailAuthorTV.text = item.author
            emailSubjectTV.text = item.subject
            emailContentTV.text = item.content
//            imageView.setImageResource(item.image!!)
        }
    }

    override fun getItemCount() = dataList.size
    fun setData(newDataList: List<Email>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }
}