package com.csstudent.manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.csstudent.manager.databinding.ActivityMainBinding
import com.csstudent.manager.ui.ByteCodingFragment
import com.csstudent.manager.ui.GithubFragment
import com.csstudent.manager.ui.DictionaryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_byte_coding)
                1 -> getString(R.string.tab_github)
                2 -> getString(R.string.tab_dictionary)
                else -> ""
            }
        }.attach()
    }

    private inner class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ByteCodingFragment()
                1 -> GithubFragment()
                2 -> DictionaryFragment()
                else -> ByteCodingFragment()
            }
        }
    }
}
