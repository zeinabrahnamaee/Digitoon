package com.example.divartask.presentation.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.divartask.R
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.databinding.FragmentPlacesBinding
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.flowLife
import com.example.divartask.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPlaces : Fragment() {

    private var _mBinding: FragmentPlacesBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<PlacesViewModel>()

    private var adapter: PlacesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlaces()
        adapter = PlacesAdapter(onItemClicked = { id ->
            goToPostsFragment(id)
        })
        collectPlacesFlow()
    }

    private fun goToPostsFragment(id: Int) {
        findNavController().navigate(R.id.fragmentPosts, bundleOf("ID" to id))
    }

    private fun collectPlacesFlow() {
        flowLife(viewModel.placesState) {
            when (it) {
                is BaseViewState.Success -> {
                    showLoading(false)
                    setupRecycler(it.data)
                }

                is BaseViewState.ErrorString -> { showToast(it.message)}
                is BaseViewState.Loading -> {
                    showLoading(true)
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.rvPlaces.visibility = View.GONE
            binding.chooseCity.visibility = View.GONE
            binding.progressLoading.visibility = View.VISIBLE
        } else {
            binding.rvPlaces.visibility = View.VISIBLE
            binding.chooseCity.visibility = View.VISIBLE
            binding.progressLoading.visibility = View.GONE
        }
    }

    private fun setupRecycler(data: PlacesListData) {
        adapter?.setData(data.cities as ArrayList<PlacesListData.City>)
        binding.rvPlaces.adapter = adapter
    }
}