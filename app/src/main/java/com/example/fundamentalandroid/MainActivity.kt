package com.example.fundamentalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_user_list)
        rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = UserListAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                moveActivity(data)
            }
        })
    }

    private fun moveActivity(user: User) {
        val moveIntent = Intent(this, UserDetail::class.java)

        moveIntent.putExtra(UserDetail.EXTRA_UNAME, user.username)
        moveIntent.putExtra(UserDetail.EXTRA_NAME, user.name)
        moveIntent.putExtra(UserDetail.EXTRA_REPO, user.repository)
        moveIntent.putExtra(UserDetail.EXTRA_PROFILE_PICTURE, user.profilePicture)
        moveIntent.putExtra(UserDetail.EXTRA_FOLLOWERS, user.followers)
        moveIntent.putExtra(UserDetail.EXTRA_FOLLOWING, user.following)
        moveIntent.putExtra(UserDetail.EXTRA_LOCATION, user.location)
        moveIntent.putExtra(UserDetail.EXTRA_COMPANY, user.company)
        startActivity(moveIntent)
    }

    private val listUsers: ArrayList<User>
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataProfilePicture = resources.obtainTypedArray(R.array.avatar)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataFollowers = resources.getStringArray(R.array.followers)
            val dataFollowing = resources.getStringArray(R.array.following)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataCompany = resources.getStringArray(R.array.company)
            val listUser = ArrayList<User>()
            for (i in dataUsername.indices){
                val user = User(dataUsername[i], dataName[i], dataProfilePicture.getResourceId(i, -1), dataRepository[i], dataFollowers[i], dataFollowing[i], dataLocation[i], dataCompany[i])
                listUser.add(user)
            }
            return listUser
        }


}