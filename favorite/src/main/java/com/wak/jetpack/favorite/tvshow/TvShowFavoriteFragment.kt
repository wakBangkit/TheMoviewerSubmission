package com.wak.jetpack.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.ui.TvShowAdapter
import com.wak.jetpack.details.DetailActivity
import com.wak.jetpack.favorite.ListFavoriteViewModel
import com.wak.jetpack.favorite.di.favoriteModule
import com.wak.jetpack.submission.databinding.FragmentTvShowBinding

import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class TvShowFavoriteFragment : Fragment() {
    private lateinit var tvBinding: FragmentTvShowBinding
    private val viewModel: ListFavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        tvBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return tvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvBinding.progressBar.visibility = View.VISIBLE
        getViewModelData()
    }

    private fun getViewModelData() {
        if (activity != null) {
            loadKoinModules(favoriteModule)

            val listAdapter = TvShowAdapter()
            listAdapter.setCallback(object : TvShowAdapter.Callback{
                override fun onItemClick(item: TvShow) {
                    sendToDetail(item)
                }
            })

            //  get tv show favorite data
            viewModel.getFavTvShow.observe(viewLifecycleOwner, { tv ->
                listAdapter.inputData(tv)
                listAdapter.notifyDataSetChanged()
                tvBinding.progressBar.visibility = View.GONE

            })

            //set recycler view
            with(tvBinding.rvListTv) {
                //make in potrait mode 2 span count
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true) // set fixed size is true
                adapter = listAdapter
            }
        }
    }
    private fun sendToDetail(item: TvShow) {
        val intentDetailUser = Intent(activity, DetailActivity::class.java)
        // start intent with send data id and index
        intentDetailUser.putExtra(DetailActivity.EXTRA_LIST_ID, item)
        intentDetailUser.putExtra(DetailActivity.EXTRA_INDEX, "2")
        startActivity(intentDetailUser)

    }
}