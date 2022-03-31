package com.example.fundamentalandroid.ui.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamentalandroid.adapter.UserFavoriteAdapter
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.databinding.ActivityFavoriteBinding
import com.example.fundamentalandroid.model.UserFavoriteViewModel
import com.example.fundamentalandroid.model.ViewModelFactory
import com.example.fundamentalandroid.ui.userDetail.UserDetail

class FavoriteActivity : AppCompatActivity() {

    private var binding: ActivityFavoriteBinding? = null
    private lateinit var viewModel: UserFavoriteViewModel
    private lateinit var adapter: UserFavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.title = "Favorite"

        viewModel = obtainViewModel(this)
        viewModel.getAllUserFavorite().observe(this@FavoriteActivity) { userFavorite ->
            if (userFavorite != null) {
                adapter.setUserFavorites(userFavorite)
            }
        }
        adapter = UserFavoriteAdapter()
        binding?.rvUserList?.layoutManager = LinearLayoutManager(this)
        binding?.rvUserList?.setHasFixedSize(true)
        binding?.rvUserList?.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onRestart() {
        super.onRestart()
        recreate()
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserFavoriteViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}