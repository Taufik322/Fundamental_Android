package com.example.fundamentalandroid.ui.userDetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundamentalandroid.R
import com.example.fundamentalandroid.adapter.UserListAdapter
import com.example.fundamentalandroid.databinding.FragmentFollowersBinding
import com.example.fundamentalandroid.model.FollowersViewModel
import com.example.fundamentalandroid.network.DataItem

class FollowersFragment : Fragment() {

    private lateinit var _binding: FragmentFollowersBinding
    private val binding get() = _binding
    private lateinit var adapter: UserListAdapter
    private lateinit var name: String
    private lateinit var viewModel: FollowersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        name = args?.getString(UserDetail.EXTRA_USER_IDENTITY).toString()
        _binding = FragmentFollowersBinding.bind(view)

        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(activity)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowersViewModel::class.java)
        viewModel.setFollowersData(name)
        viewModel.getFollowersData().observe(viewLifecycleOwner) {
            adapter = UserListAdapter(it)
            binding.rvUserList.adapter = adapter
            adapter.setOnItemClickCallback(object : UserListAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataItem) {
                    val intent = Intent(activity, UserDetail::class.java)
                    intent.putExtra(UserDetail.EXTRA_USER_IDENTITY, data.login)
                    startActivity(intent)
                }
            })
            showLoading(false)
        }
    }

    private fun showLoading(value: Boolean) {
        if (value) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_followers, container, false)
    }
}