package com.example.fundamentalandroid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fundamentalandroid.R
import com.example.fundamentalandroid.database.UserFavorite
import com.example.fundamentalandroid.databinding.ItemUserRowBinding
import com.example.fundamentalandroid.helper.UserFavoriteCallback
import com.example.fundamentalandroid.ui.userDetail.UserDetail

class UserFavoriteAdapter : RecyclerView.Adapter<UserFavoriteAdapter.UserFavoriteViewHolder>() {
    private val userFavorite = ArrayList<UserFavorite>()

    fun setUserFavorites(favorites: List<UserFavorite>) {
        val diffCallback = UserFavoriteCallback(this.userFavorite, favorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        userFavorite.clear()
        userFavorite.addAll(favorites)
        diffResult.dispatchUpdatesTo(this)
    }

    class UserFavoriteViewHolder (private val binding: ItemUserRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(fav: UserFavorite) {
            with(binding) {
                itemName.text = fav.login
                Glide.with(itemView.context).load(fav.avatarUrl).circleCrop()
                    .into(itemProfilePicture)
                itemView.setOnClickListener{
                    val intent = Intent(it.context, UserDetail::class.java)
                    intent.putExtra(UserDetail.EXTRA_USER_IDENTITY, fav.login)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserFavoriteViewHolder {
        val itemRowUserBinding = ItemUserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserFavoriteViewHolder(itemRowUserBinding)
    }

    override fun onBindViewHolder(holder: UserFavoriteViewHolder, position: Int) {
        val fav = userFavorite[position]
        holder.bind(fav)
    }

    override fun getItemCount(): Int = userFavorite.size

}