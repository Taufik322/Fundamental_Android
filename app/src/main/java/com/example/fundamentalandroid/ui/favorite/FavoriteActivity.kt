package com.example.fundamentalandroid.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundamentalandroid.R

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        supportActionBar?.title = "Favorite"
    }
}