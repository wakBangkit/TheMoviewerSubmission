package com.wak.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.wak.jetpack.submission.databinding.ActivityMainBinding
import com.wak.jetpack.home.SectionPagerAdapter
import com.wak.jetpack.submission.R

class MainActivity : AppCompatActivity() {
    companion object {
        private lateinit var mainBinding: ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val sectionPagerAdapter =
            SectionPagerAdapter(this)
        mainBinding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(mainBinding.tabs, mainBinding.viewPager) { tab, position ->
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_favorite_activity -> {
                val intent = Intent(this, Class.forName("com.wak.jetpack.favorite.FavoriteActivity"))
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}