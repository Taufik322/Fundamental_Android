package com.example.fundamentalandroid.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fundamentalandroid.ui.userDetail.FollowersFragment
import com.example.fundamentalandroid.ui.userDetail.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragmet: Fragment? = null
        when (position) {
            0 -> fragmet = FollowersFragment()
            1 -> fragmet = FollowingFragment()
        }
        fragmet?.arguments = this.fragmentBundle
        return fragmet as Fragment
    }
}