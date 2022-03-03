package com.example.fundamentalandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_USER_IDENTITY = "extra_user_identity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val tvDataUnameReceived: TextView = findViewById(R.id.tv_detail_username)
        val tvDataNameReceived: TextView = findViewById(R.id.tv_detail_name)
        val tvDataRepositoryReceived: TextView = findViewById(R.id.tv_detail_repository)
        val tvDataProfilePicture: ImageView = findViewById(R.id.im_detail_profile_picture)
        val tvDataFollowers: TextView = findViewById(R.id.tv_detail_followers)
        val tvDataFollowing: TextView = findViewById(R.id.tv_detail_following)
        val tvDataLocation: TextView = findViewById(R.id.tv_detail_location)
        val tvDataCompany: TextView = findViewById(R.id.tv_detail_company)

        val userIdentity = intent.getParcelableExtra<User>(EXTRA_USER_IDENTITY) as User
        tvDataUnameReceived.text = userIdentity.username
        tvDataNameReceived.text = userIdentity.name
        tvDataRepositoryReceived.text = "${userIdentity.repository} Repository"
        Glide.with(this).load(userIdentity.profilePicture).circleCrop().into(tvDataProfilePicture)
        tvDataFollowers.text = userIdentity.followers
        tvDataFollowing.text = userIdentity.following
        tvDataLocation.text = userIdentity.location
        tvDataCompany.text = userIdentity.company

        supportActionBar?.title = userIdentity.name
    }
}