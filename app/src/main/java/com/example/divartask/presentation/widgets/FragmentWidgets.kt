package com.example.divartask.presentation.widgets

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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentWidgets : Fragment() {

    private var _mBinding: FragmentWidgetsBinding? = null
    private val binding get() = _mBinding!!

    private val viewModel by viewModels<WidgetsViewModel>()

    private var adapter: PostsAdapter? = null
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = PostsAdapter(onItemClicked = {
            goToDetailFragment(it)
        })
        id = arguments?.getInt("ID") ?: 0
        viewModel.getWidgets(id)
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
        setupRecycler()
        collectPostsFlow()
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

    private fun setDataToRecycler(widgetList: List<WidgetsDomain>){
        if(widgetList.isNotEmpty())
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
                    if (it.findLastCompletelyVisibleItemPosition() == it.itemCount- 1) {
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

}