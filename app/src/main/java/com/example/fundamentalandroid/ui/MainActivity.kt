package com.example.fundamentalandroid.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamentalandroid.*
import com.example.fundamentalandroid.adapter.UserListAdapter
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.databinding.ActivityMainBinding
import com.example.fundamentalandroid.databinding.ActivitySettingBinding
import com.example.fundamentalandroid.model.MainViewModel
import com.example.fundamentalandroid.network.DataItem
import com.example.fundamentalandroid.ui.favorite.FavoriteActivity
import com.example.fundamentalandroid.ui.setting.*
import com.example.fundamentalandroid.ui.userDetail.UserDetail
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager

        showLoading(false)
        binding.notFound.visibility = View.GONE
        binding.notFoundText.visibility = View.GONE

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )
        viewModel.getFindUsers().observe(this@MainActivity) {
            showLoading(false)
            binding.notFound.visibility = View.INVISIBLE
            binding.notFoundText.visibility = View.INVISIBLE
            setUserData(it)
        }

        viewModel.isLoading.observe(this@MainActivity) {
            showLoading(it)
        }

        viewModel.findUsers(randomStartingList(2))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Input Name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.findUsers(query)
                binding.notFound.visibility = View.INVISIBLE
                binding.notFoundText.visibility = View.INVISIBLE
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return true
        }
    }

    private fun setUserData(userData: List<DataItem>) {
        showLoading(false)
        val adapter = UserListAdapter(userData)
        binding.rvUserList.adapter = adapter
        if (adapter.itemCount == 0) {
            binding.notFoundText.visibility = View.VISIBLE
            binding.notFound.visibility = View.VISIBLE
        }

        adapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DataItem) {
                val intent = Intent(this@MainActivity, UserDetail::class.java)
                intent.putExtra(UserDetail.EXTRA_USER_IDENTITY, data.login)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(value: Boolean) {
        if (value) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun randomStartingList(length: Int): String {
        val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { alphabet.random() }
            .joinToString("")
    }
}