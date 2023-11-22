package com.example.divartask.presentation.detail

import android.os.Bundle
import android.text.Spannable.Factory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.divartask.R
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.databinding.FragmentDetailBinding
import com.example.divartask.databinding.FragmentPlacesBinding
import com.example.divartask.databinding.FragmentPostsBinding
import com.example.divartask.presentation.places.PlacesViewModel
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.DetailWidgetTypeEnum
import com.example.divartask.presentation.util.flowLife
import com.example.divartask.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import org.koin.core.parameter.parametersOf
import javax.annotation.meta.When
import kotlin.math.log

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    private var _mBinding: FragmentDetailBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<DetailViewModel>()

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

        collectPostsFlow()
    }


    private fun collectPostsFlow() {
        flowLife(viewModel.detailState){
            when(it){
                is BaseViewState.Success -> {
                    showLoading(false)
                }
                is BaseViewState.Loading -> {
                    showLoading(true)
                }

                is BaseViewState.ErrorString -> {
                    showToast(it.message)
                }
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.progressLoading.visibility = View.VISIBLE
        } else {
            binding.progressLoading.visibility = View.GONE
        }
    }

}