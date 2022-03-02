package com.example.fundamentalandroid

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_UNAME = "extra_uname"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_REPO = "extra_repo"
        const val EXTRA_PROFILE_PICTURE = "extra_profile_picture"
        const val EXTRA_FOLLOWERS = "extra_followers"
        const val EXTRA_FOLLOWING = "extra_following"
        const val EXTRA_LOCATION = "extra_location"
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

        val uname = intent.getStringExtra(EXTRA_UNAME)
        val name = intent.getStringExtra(EXTRA_NAME)
        val repo = intent.getStringExtra(EXTRA_REPO)
        val repoText = "$repo Repostiory"
        val profilePicture = intent.getIntExtra(EXTRA_PROFILE_PICTURE, 0)
        val followers = intent.getStringExtra(EXTRA_FOLLOWERS)
        val following = intent.getStringExtra(EXTRA_FOLLOWING)
        val location = intent.getStringExtra(EXTRA_LOCATION)

        tvDataUnameReceived.text = uname
        tvDataNameReceived.text = name
        tvDataRepositoryReceived.text = repoText
        Glide.with(this).load(profilePicture).circleCrop().into(tvDataProfilePicture)
        tvDataFollowers.text = followers
        tvDataFollowing.text = following
        tvDataLocation.text = location

        supportActionBar?.title = name
    }
}