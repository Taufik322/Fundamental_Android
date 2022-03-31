package com.example.fundamentalandroid.ui.userDetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.fundamentalandroid.R
import com.example.fundamentalandroid.adapter.SectionsPagerAdapter
import com.example.fundamentalandroid.adapter.UserFavoriteAdapter
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.databinding.ActivityUserDetailConstraintBinding
import com.example.fundamentalandroid.model.UserDetailViewModel
import com.example.fundamentalandroid.model.ViewModelFactory
import com.example.fundamentalandroid.network.UserDetailApi
import com.example.fundamentalandroid.ui.favorite.FavoriteActivity
import com.google.android.material.tabs.TabLayoutMediator

class UserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailConstraintBinding
    private lateinit var viewModel: UserDetailViewModel
    private lateinit var bundle: Bundle

    private var userFavorite: UserFavorite? = null
    private lateinit var userDetailApi: UserDetailApi
    private var favIcon: Boolean = false

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
            ViewModelFactory.getInstance(application)
        ).get(UserDetailViewModel::class.java)

        if (userIdentity != null) {
            viewModel.setUserDetail(userIdentity)
        }

        viewModel.getUserDetail().observe(this) {
            setUserDetail(it)
            userDetailApi = it
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

        binding.favoriteIcon.setOnClickListener {
//            add to favorite
            if (!favIcon) {
                binding.favoriteIcon.setImageResource(R.drawable.ic_favorite_true)
                addUserFavorite(userFavorite)
                viewModel.getUserDetail().observe(this) {
                    showToast(it, true)
                }
                favIcon = true
//            remove from favorite
            } else {
                binding.favoriteIcon.setImageResource(R.drawable.ic_favorite_false)
                viewModel.removeFromFavorite(userDetailApi.id)
                viewModel.getUserDetail().observe(this) {
                    showToast(it, false)
                }
                favIcon = false
            }
        }
    }

    private fun addUserFavorite(identity: UserFavorite?) {
        if (identity != null) {
            userFavorite?.id = identity.id
            userFavorite?.login = identity.login
            userFavorite?.avatarUrl = identity.avatarUrl
        }
        viewModel.addToFavorite(userFavorite as UserFavorite)
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

        viewModel.userDetailList.observe(this) {
            userFavorite = UserFavorite(it.id, it.login, it.avatarUrl)
            viewModel.getFavorites().observe(this) {
                if (it != null) {
                    for (i in it) {
                        if (userData.id == i.id) {
                            favIcon = true
                            binding.favoriteIcon.setImageResource(R.drawable.ic_favorite_true)
                        }
                    }
                }
            }
        }
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
            binding.favoriteIcon.visibility = View.GONE
        } else {
            binding.loading.visibility = View.GONE
            binding.tvDetailCompany.visibility = View.VISIBLE
            binding.tvDetailLocation.visibility = View.VISIBLE
            binding.tvDetailFollowers.visibility = View.VISIBLE
            binding.tvDetailFollowers2.visibility = View.VISIBLE
            binding.tvDetailFollowing.visibility = View.VISIBLE
            binding.tvDetailFollowing2.visibility = View.VISIBLE
            binding.tvDetailRepository.visibility = View.VISIBLE
            binding.favoriteIcon.visibility = View.VISIBLE

        }
    }

    private fun showToast(uname: UserDetailApi, add: Boolean) {
        if (add) {
            Toast.makeText(this, "${uname.login} added to favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${uname.login} removed from favorite", Toast.LENGTH_SHORT).show()
        }
    }
}