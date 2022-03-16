package com.example.fundamentalandroid

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamentalandroid.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUserList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUserList.addItemDecoration(itemDecoration)
        showLoading(false)
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
                findUsers(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    private fun findUsers(name: String){
        showLoading(true)
        val client = ApiConfig.getApiService().getUsersSearch(name)
        client.enqueue(object : Callback<UsersResponse>{
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody.items)
                    }
                } else {
                    Log.e("MainActivity", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun setUserData(userData: List<DataItem>){
        showLoading(false)
        val listUserData = ArrayList<UserInfo>()
        for(data in userData){
            val user = UserInfo(data.login, data.avatarUrl)
            listUserData.addAll(listOf(user))
        }
        val adapter = UserListAdapter(listUserData)
        binding.rvUserList.adapter = adapter
    }

    private fun showLoading(value: Boolean){
        if (value){
            binding.progressBar.visibility = View.VISIBLE
        } else{
            binding.progressBar.visibility = View.GONE
        }
    }
}