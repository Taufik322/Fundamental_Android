package com.example.fundamentalandroid

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_UNAME = "extra_uname"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_REPO = "extra_repo"
        const val EXTRA_PROFILE_PICTURE = "extra_profile_picture"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val tvDataUnameReceived: TextView = findViewById(R.id.tv_detail_username)
        val tvDataNameReceived: TextView = findViewById(R.id.tv_detail_name)
        val tvDataRepositoryReceived: TextView = findViewById(R.id.tv_detail_repository)
        val tvDataProfilePicture: ImageView = findViewById(R.id.im_detail_profile_picture)

        val uname = intent.getStringExtra(EXTRA_UNAME)
        val name = intent.getStringExtra(EXTRA_NAME)
        val repo = intent.getStringExtra(EXTRA_REPO)
        val repoText = "$repo Repostiory"
        val profilePicture = intent.getIntExtra(EXTRA_PROFILE_PICTURE, 0)

        tvDataUnameReceived.text = uname
        tvDataNameReceived.text = name
        tvDataRepositoryReceived.text = repoText
        tvDataProfilePicture.setImageResource(profilePicture)

        supportActionBar?.title = name
    }
}