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
import com.example.fundamentalandroid.databinding.FragmentFollowingBinding
import com.example.fundamentalandroid.model.FollowingViewModel
import com.example.fundamentalandroid.network.DataItem

class FollowingFragment : Fragment() {

    private lateinit var _binding: FragmentFollowingBinding
    private val binding get() = _binding
    private lateinit var adapter: UserListAdapter
    private lateinit var name: String
    private lateinit var viewModel: FollowingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        name = args?.getString(UserDetail.EXTRA_USER_IDENTITY).toString()
        _binding = FragmentFollowingBinding.bind(view)

        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(activity)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FollowingViewModel::class.java)
        viewModel.setFollowingData(name)
        viewModel.getFollowingData().observe(viewLifecycleOwner) {
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
         return inflater.inflate(R.layout.fragment_following, container, false)
    }
}