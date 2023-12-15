package com.example.digitoon.presentation.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.digitoon.R
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.databinding.FragmentMoviesBinding
import com.example.digitoon.presentation.util.BaseViewState
import com.example.digitoon.presentation.util.flowLife
import com.example.digitoon.presentation.util.putIdInSharedPref
import com.example.digitoon.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentMovies : Fragment() {

    private var _mBinding: FragmentMoviesBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<MoviesViewModel>()

    private var adapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        adapter = MoviesAdapter(onItemClicked = { id ->
            context?.putIdInSharedPref(id)
            goDetailFragment()
        })
        collectPlacesFlow()
    }

    private fun goDetailFragment() {
        findNavController().navigate(R.id.fragmentDetail)
    }

    private fun collectPlacesFlow() {
        flowLife(viewModel.moviesState) {
            when (it) {
                is BaseViewState.Success -> {
                    showLoading(false)
                    setupRecycler(it.data.search ?: listOf())
                }

                is BaseViewState.ErrorString -> {
                    showLoading(false)
                    showToast(it.message)
                }

                is BaseViewState.Loading -> {
                    showLoading(true)
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.rvMovies.visibility = View.GONE
            binding.chooseMovie.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.rvMovies.visibility = View.VISIBLE
            binding.chooseMovie.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupRecycler(data: List<MoviesData.Search>) {
        adapter?.setData(data)
        binding.rvMovies.adapter = adapter
    }
}