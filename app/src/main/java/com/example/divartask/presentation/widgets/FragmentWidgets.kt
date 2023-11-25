package com.example.divartask.presentation.widgets

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.divartask.R
import com.example.divartask.databinding.FragmentWidgetsBinding
import com.example.divartask.domain.model.WidgetsDomain
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.flowLife
import com.example.divartask.presentation.util.showToast
import android.location.LocationListener
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.divartask.presentation.util.LocationRequestCreator
import com.example.divartask.presentation.util.getIdFromSharedPref
import com.example.divartask.presentation.util.gpsIsOn
import com.example.divartask.presentation.util.iF
import com.example.divartask.presentation.util.isPermittedLocationAccess
import com.example.divartask.presentation.util.locationFlow
import com.example.divartask.presentation.util.putIdInSharedPref
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentWidgets : Fragment() {

    private var _mBinding: FragmentWidgetsBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<WidgetsViewModel>()

    private var adapter: PostsAdapter? = null
    private var id: Int = 0

    private var fusedLocationClient: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PostsAdapter(onItemClicked = {
            goToDetailFragment(it)
        })
        id = context?.getIdFromSharedPref()?: 1
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (id != 0) viewModel.getWidgets(id) else startLocationListening()
    }

    private fun goToDetailFragment(token: String) {
        findNavController().navigate(R.id.fragmentDetail, bundleOf("TOKEN" to token))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentWidgetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiListener()
        setupRecycler()
        collectPostsFlow()
        collectLocationFlow()
    }

    private fun setupUiListener() {
        binding.chooseCity.setOnClickListener {
            findNavController().navigate(R.id.fragmentPlaces)
        }
    }


    private fun collectPostsFlow() {
        flowLife(viewModel.widgetsState) {
            when (it) {
                is BaseViewState.Success -> {
                    showLoading(false)
                    adapter?.showLoading(false)
                    setDataToRecycler(it.data)
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

    private fun collectLocationFlow() {
        flowLife(viewModel.locationState) {
            when (it) {
                is BaseViewState.Success -> {
                    binding.cityTitle.text = it.data.cityName
                    context?.putIdInSharedPref(it.data.cityId)
                    viewModel.getWidgets(it.data.cityId)
                }

                is BaseViewState.ErrorString -> {
                    viewModel.getWidgets(id)
                    showLoading(false)
                    showToast(it.message)
                }

                is BaseViewState.Loading -> {
                    showLoading(true)
                }
            }
        }
    }

    private fun setDataToRecycler(widgetList: List<WidgetsDomain>) {
        if (widgetList.isNotEmpty())
            adapter?.setData(widgetList as ArrayList<WidgetsDomain>)
    }

    private fun setupRecycler() {
        binding.rvPosts.adapter = adapter
        binding.rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as? LinearLayoutManager)?.let {
                    if (it.findLastCompletelyVisibleItemPosition() == it.itemCount - 1) {
                        adapter?.showLoading(true)
                        viewModel.getWidgets(id)
                    }
                }

            }
        })
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.rvPosts.visibility = View.GONE
            binding.progressLoading.visibility = View.VISIBLE
        } else {
            binding.rvPosts.visibility = View.VISIBLE
            binding.progressLoading.visibility = View.GONE
        }
    }


    private fun startLocationListening() {

        activity?.let {
            it.gpsIsOn().iF({
                it.isPermittedLocationAccess().iF({

                    lifecycleScope.launch {
                        fusedLocationClient?.locationFlow(LocationRequestCreator.create())
                            ?.flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                            ?.collect { location ->
                                viewModel.getUserCity(location.latitude, location.longitude)
                            }
                    }

                }, {
                    viewModel.getWidgets(id)
                })
            }, {
                viewModel.getWidgets(id)
            })
        }
    }
}