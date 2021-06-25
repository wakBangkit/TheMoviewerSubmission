package com.wak.jetpack.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.ui.MovieAdapter
import com.wak.jetpack.submission.databinding.FragmentMovieBinding
import com.wak.jetpack.details.DetailActivity
import com.wak.jetpack.favorite.ListFavoriteViewModel
import com.wak.jetpack.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class MovieFavoriteFragment : Fragment() {
    private lateinit var movieBinding :FragmentMovieBinding
    private val viewModel: ListFavoriteViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return movieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBinding.progressBar.visibility = View.VISIBLE
        getViewModelData()
    }

    private fun getViewModelData() {
        if (activity != null) {
            loadKoinModules(favoriteModule)
            val listAdapter = MovieAdapter()
            listAdapter.setCallback(object : MovieAdapter.Callback{
                override fun onItemClick(item: Movie) {
                    sendToDetail(item)
                }

            })
                // get favorite movie data
                viewModel.getFavMovies.observe(viewLifecycleOwner,{movies ->

                    listAdapter.inputData(movies)
                    listAdapter.notifyDataSetChanged()
                    movieBinding.progressBar.visibility = View.GONE
                })

            //set recycler view
            with(movieBinding.rvListMovie) {
                //make in potrait mode 2 span count
                layoutManager = GridLayoutManager(context,2)
                setHasFixedSize(true) // set fixed size is true
                adapter = listAdapter
            }
        }
    }

    private fun sendToDetail(item: Movie) {
        val intentDetailUser = Intent(activity, DetailActivity::class.java)
        // start intent with send data id and index
        intentDetailUser.putExtra(DetailActivity.EXTRA_LIST_ID, item)
        intentDetailUser.putExtra(DetailActivity.EXTRA_INDEX, "1")
        startActivity(intentDetailUser)
    }
}