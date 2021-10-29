package me.life.productsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.life.productsearch.databinding.ActivityMyOrdersBinding
import me.life.productsearch.model.ResultData
import me.life.productsearch.ui.adapter.OrderAdapter
import me.life.productsearch.ui.viewmodel.OrderViewModel

@AndroidEntryPoint
class MyOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyOrdersBinding
    private lateinit var orderAdapter: OrderAdapter
    private val viewModel: OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        orderAdapter = OrderAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyOrdersActivity)
            adapter = orderAdapter
        }

        viewModel.searchProducts()

        viewModel.response.observe(this, {
            run {
                when (it) {
                    is ResultData.Success -> {
                        orderAdapter.submitList(it.data?.data)
                    }
                    is ResultData.Loading -> {

                    }
                    else -> {
                    }
                }

            }
        })
    }
}