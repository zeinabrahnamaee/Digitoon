package com.example.divartask.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.divartask.databinding.FragmentDetailBinding
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.flowLife
import com.example.divartask.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    private var _mBinding: FragmentDetailBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<DetailViewModel>()

    private var adapter: DetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDetail(arguments?.getString("TOKEN") ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DetailAdapter()
        collectDetailsFlow()
    }


    private fun collectDetailsFlow() {
        flowLife(viewModel.detailState){
            when(it){
                is BaseViewState.Success -> {
                    showLoading(false)
                    setupAdapter(it.data)
                }
                is BaseViewState.Loading -> {
                    showLoading(true)
                }

                is BaseViewState.ErrorString -> {
                    showToast(it.message)
                    showLoading(false)
                }
            }
        }
    }

    private fun setupAdapter(widgets: List<DetailDomain>) {
        adapter?.setData(widgets as ArrayList<DetailDomain>)
        binding.rvDetail.adapter = adapter
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.progressLoading.visibility = View.VISIBLE
            binding.rvDetail.visibility = View.GONE
        } else {
            binding.progressLoading.visibility = View.GONE
            binding.rvDetail.visibility = View.VISIBLE
        }
    }

}