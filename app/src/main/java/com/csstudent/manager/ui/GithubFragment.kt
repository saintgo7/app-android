package com.csstudent.manager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.csstudent.manager.R
import com.csstudent.manager.api.GithubApiService
import com.csstudent.manager.data.GithubRepository
import com.csstudent.manager.databinding.FragmentGithubBinding
import kotlinx.coroutines.launch

class GithubFragment : Fragment() {

    private var _binding: FragmentGithubBinding? = null
    private val binding get() = _binding!!
    private val apiService = GithubApiService.create()
    private lateinit var repoAdapter: RepositoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGithubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()
    }

    private fun setupRecyclerView() {
        repoAdapter = RepositoryAdapter()
        binding.recyclerRepos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            searchUser()
        }

        binding.inputUsername.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchUser()
                true
            } else {
                false
            }
        }
    }

    private fun searchUser() {
        val username = binding.inputUsername.text.toString().trim()
        if (username.isEmpty()) {
            Toast.makeText(context, "사용자명을 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                binding.progressBar.isVisible = true
                binding.userInfoCard.isVisible = false
                binding.tvReposTitle.isVisible = false

                val userResponse = apiService.getUser(username)
                if (userResponse.isSuccessful && userResponse.body() != null) {
                    val user = userResponse.body()!!
                    binding.tvUsername.text = user.name ?: user.login
                    binding.tvBio.text = user.bio ?: "설명이 없습니다"
                    binding.tvReposCount.text = user.publicRepos.toString()
                    binding.tvFollowersCount.text = user.followers.toString()
                    binding.tvFollowingCount.text = user.following.toString()
                    binding.userInfoCard.isVisible = true

                    val reposResponse = apiService.getUserRepos(username)
                    if (reposResponse.isSuccessful && reposResponse.body() != null) {
                        val repos = reposResponse.body()!!
                        repoAdapter.submitList(repos)
                        binding.tvReposTitle.isVisible = true
                    }
                } else {
                    Toast.makeText(context, R.string.error_user_not_found, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "${getString(R.string.error_network)}: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.isVisible = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
