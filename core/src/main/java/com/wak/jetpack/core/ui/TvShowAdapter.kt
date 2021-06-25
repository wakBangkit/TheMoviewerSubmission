package com.wak.jetpack.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wak.jetpack.core.R
import com.wak.jetpack.core.databinding.ItemCardviewBinding
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.utils.Constant

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {
    private var listData: ArrayList<TvShow> = ArrayList()
    private var callback: Callback? = null


    fun inputData(dataIn: List<TvShow>?) {
        if (dataIn == null) return
        this.listData.clear()
        this.listData.addAll(dataIn)
    }

    inner class ListViewHolder(private val binding: ItemCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvShow) {
            binding.apply {
                // Ambil Gambar
                Glide.with(itemView.context)
                    .load(Constant.IMAGE_BASE_URL + data.poster) // load using image base url with desired dimention
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_refresh)
                            .error(R.drawable.ic_error_load)
                    )
                    .into(rvImage)
                tvRvTitle.text = data.title

                itemView.setOnClickListener {
                    callback?.onItemClick(data)
                }
            }
        }

    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun onItemClick(item: TvShow)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemCardviewBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}