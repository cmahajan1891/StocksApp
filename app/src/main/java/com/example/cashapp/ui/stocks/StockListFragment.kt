package com.example.cashapp.ui.stocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cashapp.CashApplication
import com.example.cashapp.databinding.FragmentStockListBinding
import com.example.cashapp.di.components.DaggerFragmentComponent
import com.example.cashapp.di.modules.FragmentModule
import com.example.cashapp.utils.display.Toaster
import javax.inject.Inject

class StockListFragment : Fragment() {

    @Inject
    lateinit var viewModel: StockListFragmentViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var stocksAdapter: StocksAdapter

    private lateinit var binding: FragmentStockListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.stocks.observe(this, { it.data?.run { stocksAdapter.submitList(this) } })
        viewModel.successState.observe(this, { binding.successStateVisibility = it })
        viewModel.errorState.observe(this, { binding.errorStateVisibility = it })
        viewModel.loadingState.observe(this, { binding.loadingStateVisibility = it })
        viewModel.emptyState.observe(this, { binding.emptyStateVisibility = it })
        viewModel.messageStringId.observe(this, { it.data?.run { showMessage(resId = it.data) } })
        viewModel.messageString.observe(this, { it.data?.run { showMessage(message = it.data) } })
    }

    private fun showMessage(message: String) = context?.let { Toaster.show(it, message) }

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.rvStocks.apply {
            layoutManager = linearLayoutManager
            adapter = stocksAdapter
        }
    }

    private fun injectDependencies() {
        DaggerFragmentComponent
            .builder()
            .applicationComponent((requireContext().applicationContext as CashApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockListBinding.inflate(inflater, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    companion object {

        const val TAG = "StockListFragment"

        fun newInstance(): StockListFragment {
            val args = Bundle()
            val fragment = StockListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
