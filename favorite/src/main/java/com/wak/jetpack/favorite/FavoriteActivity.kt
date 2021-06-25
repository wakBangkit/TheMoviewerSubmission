package com.wak.jetpack.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.wak.jetpack.favorite.databinding.ActivityFavoriteBinding
import com.wak.jetpack.submission.R

class FavoriteActivity : AppCompatActivity() {
    companion object {
        private lateinit var favBinding: ActivityFavoriteBinding
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "moviewer favorite"
        setUpViewPager()
    }
    private fun setUpViewPager() {
        val sectionPagerAdapter =
            SectionFavoritePagerAdapter(this)
        favBinding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(favBinding.tabs, favBinding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getString(R.string.movie)
                    tab.setIcon(R.drawable.ic_movie_gray)
                }
                1 -> {
                    tab.text = resources.getString(R.string.tv_show)
                    tab.setIcon(R.drawable.ic_tvshow_gray)
                }
            }
        }.attach()
        supportActionBar?.elevation = 0f
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}