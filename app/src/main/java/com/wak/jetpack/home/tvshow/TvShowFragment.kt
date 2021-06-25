package com.wak.jetpack.home.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.ui.TvShowAdapter
import com.wak.jetpack.core.data.source.Resource
import com.wak.jetpack.submission.databinding.FragmentTvShowBinding
import com.wak.jetpack.details.DetailActivity
import com.wak.jetpack.home.ListDataViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private lateinit var tvBinding: FragmentTvShowBinding
    private val viewModel: ListDataViewModel by viewModel()

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
            val listAdapter = TvShowAdapter()
            listAdapter.setCallback(object : TvShowAdapter.Callback{
                override fun onItemClick(item: TvShow) {
                    sendToDetail(item)
                }

            })

            //  get tv data from retrofit
            viewModel.getTvShow.observe(viewLifecycleOwner, { tv ->
                if(tv != null){
                    when(tv){
                        //while loading
                        is Resource.Loading -> tvBinding.progressBar.visibility = View.VISIBLE
                        //while success
                        is Resource.Success ->{
                            tvBinding.progressBar.visibility = View.GONE
                            listAdapter.inputData(tv.data)
                            listAdapter.notifyDataSetChanged()
                        }
                        //while error
                        is Resource.Error ->{
                            tvBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Something Wrong :(", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
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