package com.example.digitoon.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.digitoon.data.remote.entity.DetailData
import com.example.digitoon.databinding.FragmentDetailBinding
import com.example.digitoon.presentation.util.BaseViewState
import com.example.digitoon.presentation.util.flowLife
import com.example.digitoon.presentation.util.getIdFromSharedPref
import com.example.digitoon.presentation.util.loadImageToView
import com.example.digitoon.presentation.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : Fragment() {

    private var _mBinding: FragmentDetailBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDetail(context?.getIdFromSharedPref()?:"")
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

        collectDetailsFlow()
    }


    private fun collectDetailsFlow() {
        flowLife(viewModel.detailState){
            when(it){
                is BaseViewState.Success -> {
                    showLoading(false)
                    setupUi(it.data)
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

    private fun setupUi(data: DetailData) {
        with(binding){
            logo.loadImageToView(data.poster?:"")
            title.text = data.title
            released.text = data.released
            runTime.text = data.runtime
            genre.text = data.genre
            director.text = data.director
            writer.text = data.writer
            actors.text = data.actors
            plot.text = data.plot
            language.text = data.language
            country.text = data.country
            awards.text = data.awards
            imdb.text = data.imdbRating
        }
    }

    private fun showLoading(isShow: Boolean) {
        if (isShow) {
            binding.progressLoading.visibility = View.VISIBLE
        } else {
            binding.progressLoading.visibility = View.GONE
            binding.groupTitles.visibility = View.VISIBLE
        }
    }

}