package com.example.fundamentalandroid.ui.userDetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fundamentalandroid.R
import com.example.fundamentalandroid.adapter.SectionsPagerAdapter
import com.example.fundamentalandroid.databinding.ActivityUserDetailConstraintBinding
import com.example.fundamentalandroid.model.UserDetailViewModel
import com.example.fundamentalandroid.network.UserDetailApi
import com.google.android.material.tabs.TabLayoutMediator

class UserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailConstraintBinding
    private lateinit var viewModel: UserDetailViewModel
    private lateinit var bundle: Bundle

    companion object {
        const val EXTRA_USER_IDENTITY = "extra_user_identity"

        private val TAB_TITTLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userIdentity = intent.getStringExtra(EXTRA_USER_IDENTITY)
        supportActionBar?.title = userIdentity

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserDetailViewModel::class.java)

        if (userIdentity != null) {
            viewModel.setUserDetail(userIdentity)
        }

        viewModel.getUserDetail().observe(this) {
            setUserDetail(it)
        }

        showLoading(true)

        bundle = Bundle()
        bundle.putString(EXTRA_USER_IDENTITY, userIdentity)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            binding.viewPager.adapter = sectionsPagerAdapter
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()



    }

    private fun setUserDetail(userData: UserDetailApi) {
        showLoading(false)
        if (userData.name == null) {
            binding.tvDetailName.text = "No Name"
        } else {
            binding.tvDetailName.text = userData.name
        }

        if (userData.location == null) {
            binding.tvDetailLocation.text = "Unknown"
        } else {
            binding.tvDetailLocation.text = userData.location
        }

        if (userData.company == null) {
            binding.tvDetailCompany.text = "Unknown"
        } else {
            binding.tvDetailCompany.text = userData.company
        }

        if (userData.repository.toInt() == 0) {
            binding.tvDetailLocation.text = "No Repository"
        } else {
            binding.tvDetailRepository.text = "${userData.repository} Repository"
        }

        binding.tvDetailFollowers.text = userData.followers
        binding.tvDetailFollowing.text = userData.following
        binding.tvDetailUsername.text = userData.login
        Glide.with(this).load(userData.avatarUrl).circleCrop().into(binding.imDetailProfilePicture)
    }

    private fun showLoading(value: Boolean) {
        if (value) {
            binding.loading.visibility = View.VISIBLE
            binding.tvDetailCompany.visibility = View.GONE
            binding.tvDetailLocation.visibility = View.GONE
            binding.tvDetailFollowers.visibility = View.GONE
            binding.tvDetailFollowers2.visibility = View.GONE
            binding.tvDetailFollowing.visibility = View.GONE
            binding.tvDetailFollowing2.visibility = View.GONE
            binding.tvDetailRepository.visibility = View.GONE
        } else {
            binding.loading.visibility = View.GONE
            binding.tvDetailCompany.visibility = View.VISIBLE
            binding.tvDetailLocation.visibility = View.VISIBLE
            binding.tvDetailFollowers.visibility = View.VISIBLE
            binding.tvDetailFollowers2.visibility = View.VISIBLE
            binding.tvDetailFollowing.visibility = View.VISIBLE
            binding.tvDetailFollowing2.visibility = View.VISIBLE
            binding.tvDetailRepository.visibility = View.VISIBLE
        }
    }
}