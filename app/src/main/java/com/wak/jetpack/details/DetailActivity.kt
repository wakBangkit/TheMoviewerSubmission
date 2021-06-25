package com.wak.jetpack.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wak.jetpack.submission.R
import com.wak.jetpack.core.domain.model.Movie
import com.wak.jetpack.core.domain.model.TvShow
import com.wak.jetpack.core.utils.Constant.IMAGE_BASE_URL
import com.wak.jetpack.submission.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_LIST_ID = "extra_data"
        const val EXTRA_INDEX = "extra_index"
    }

    private lateinit var storedMovie: Movie
    private lateinit var storedTv : TvShow
    private lateinit var detailBinding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    private var state: Boolean = false
    private var favState: Boolean = false
    private var fragmentIndex: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        state = false
        detailBinding.btnFavorite.setOnClickListener(this)
        xmlPropertiesGone(false)
        setViewModel()
    }

    private fun setViewModel() {
        fragmentIndex = intent.getStringExtra(EXTRA_INDEX)
        if(fragmentIndex == "1"){
            val data = intent.getParcelableExtra<Movie>(EXTRA_LIST_ID)
            showMovieDetail(data)
        }
        else{
            val data = intent.getParcelableExtra<TvShow>(EXTRA_LIST_ID)
            showTvDetail(data)
        }

    }

    private fun showTvDetail(data: TvShow?) {
        storedTv = data!!
        detailBinding.tvDataTitle.text = data.title
        detailBinding.tvDataDesc.text = data.desc
        detailBinding.tvDataYear.text = "(${data.year})"
        supportActionBar!!.title = data.title
        Glide.with(this)
            .load(IMAGE_BASE_URL + data.poster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_error_load)
            )
            .into(detailBinding.imgPoster)
        Glide.with(this)
            .load(IMAGE_BASE_URL + data.image)
            .into(detailBinding.imgPicture)
        favState = data.stateFav
        setFavoriteButton(favState)
        state = true
        xmlPropertiesGone(state)
    }

    private fun showMovieDetail(data: Movie?) {
        storedMovie = data!!
        detailBinding.tvDataTitle.text = data.title
        detailBinding.tvDataDesc.text = data.desc
        detailBinding.tvDataYear.text = "(${data.year})"
        supportActionBar!!.title = data.title
        Glide.with(this)
            .load(IMAGE_BASE_URL + data.poster)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_refresh)
                    .error(R.drawable.ic_error_load)
            )
            .into(detailBinding.imgPoster)
        Glide.with(this)
            .load(IMAGE_BASE_URL + data.image)
            .into(detailBinding.imgPicture)
        favState = data.stateFav
        setFavoriteButton(favState)
        state = true
        xmlPropertiesGone(state)
    }


    private fun xmlPropertiesGone(state: Boolean) {
        if (!state) {
            detailBinding.tvDataTitle.visibility = View.INVISIBLE
            detailBinding.tvDataYear.visibility = View.INVISIBLE
            detailBinding.tvOverview.visibility = View.INVISIBLE
            detailBinding.tvDataDesc.visibility = View.INVISIBLE
        } else {
            detailBinding.tvDataTitle.visibility = View.VISIBLE
            detailBinding.tvDataYear.visibility = View.VISIBLE
            detailBinding.tvOverview.visibility = View.VISIBLE
            detailBinding.tvDataDesc.visibility = View.VISIBLE
        }

    }

    private fun setFavoriteButton(isFavorite: Boolean) {
        if (isFavorite) {
            detailBinding.btnFavorite.setBackgroundResource(R.drawable.ic_favorite_true)
        } else {
            detailBinding.btnFavorite.setBackgroundResource(R.drawable.ic_favorite_false)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_favorite -> {
                favState = !favState
                setFavoriteButton(favState)
                if(fragmentIndex == "1"){ // hile 1 will update to favorite movie
                    detailViewModel.updateFavoriteMovie(storedMovie)
                }
                else{ //while 1 will update to favorite tv Show
                    detailViewModel.updateFavoriteTvShow(storedTv)
                }

            }
        }
    }
}