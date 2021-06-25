package com.wak.jetpack.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wak.jetpack.favorite.movie.MovieFavoriteFragment
import com.wak.jetpack.favorite.tvshow.TvShowFavoriteFragment


class SectionFavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment() // open movie favorite fragment
            1 -> fragment = TvShowFavoriteFragment()// open tv show favorite fragment
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}