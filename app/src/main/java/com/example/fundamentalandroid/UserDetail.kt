package com.example.fundamentalandroid

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fundamentalandroid.databinding.ActivityUserDetailConstraintBinding

class UserDetail : AppCompatActivity() {

    private lateinit var binding : ActivityUserDetailConstraintBinding

    companion object {
        const val EXTRA_USER_IDENTITY = "extra_user_identity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailConstraintBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userIdentity = intent.getParcelableExtra<User>(EXTRA_USER_IDENTITY) as User
        binding.tvDetailUsername.text = userIdentity.username
        binding.tvDetailName.text = userIdentity.name
        Glide.with(this).load(userIdentity.profilePicture).circleCrop().into(binding.imDetailProfilePicture)
        binding.tvDetailFollowers.text = userIdentity.followers
        binding.tvDetailFollowing.text = userIdentity.following
        binding.tvDetailLocation.text = userIdentity.location

        supportActionBar?.title = userIdentity.name
    }
}