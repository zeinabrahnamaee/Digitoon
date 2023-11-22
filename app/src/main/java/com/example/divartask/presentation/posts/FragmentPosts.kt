package com.example.divartask.presentation.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.databinding.FragmentPlacesBinding
import com.example.divartask.databinding.FragmentPostsBinding
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.flowLife
import dagger.hilt.android.AndroidEntryPoint
import org.koin.core.parameter.parametersOf
import kotlin.math.log

@AndroidEntryPoint
class FragmentPosts : Fragment() {

    private var _mBinding: FragmentPostsBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<PostsViewModel>()

    private var adapter: PostsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PostsAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPosts(arguments?.getInt("ID") ?: 0)

        collectPostsFlow()
    }


    private fun collectPostsFlow() {
        flowLife(viewModel.postsState) {
            when (it) {
                is BaseViewState.Success -> {
                    showLoading(false)
                    binding.rvPosts.adapter = adapter
                    adapter?.submitList(it.data.widgetList)
                    Log.e("DataResponse", it.data.toString(), )
                }

                is BaseViewState.ErrorString -> {}
                is BaseViewState.Loading -> {
                    showLoading(true)
                }
            }
        }
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

}