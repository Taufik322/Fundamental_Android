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

        val userIdentity = intent.getParcelableExtra<UserInfo>(EXTRA_USER_IDENTITY) as UserInfo
        binding.tvDetailUsername.text = userIdentity.login
        Glide.with(this).load(userIdentity.avatar).circleCrop().into(binding.imDetailProfilePicture)

        supportActionBar?.title = userIdentity.login
    }
}