package com.example.fundamentalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class UserDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_UNAME = "extra_uname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val tvDataUnameReceived: TextView = findViewById(R.id.tv_detail_username)
        val uname = intent.getStringExtra(EXTRA_UNAME)

        tvDataUnameReceived.text = uname
    }
}