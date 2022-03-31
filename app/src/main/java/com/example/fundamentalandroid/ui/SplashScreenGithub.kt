package com.example.fundamentalandroid.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.fundamentalandroid.R
import com.example.fundamentalandroid.ui.setting.SettingPreferences
import com.example.fundamentalandroid.ui.setting.SettingViewModel
import com.example.fundamentalandroid.ui.setting.SettingViewModelFactory

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SplashScreenGithub : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screeen_github)

        val delayTime: Long = 3000

        Handler(Looper.getMainLooper()).postDelayed({
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, delayTime)

        val pref = SettingPreferences.getInstance(dataStore)
        val viewModelSetting =
            ViewModelProvider(this, SettingViewModelFactory(pref)).get(SettingViewModel::class.java)

        viewModelSetting.getThemeSettings().observe(this) {
                isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}