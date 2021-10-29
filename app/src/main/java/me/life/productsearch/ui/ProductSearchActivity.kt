package me.life.productsearch.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.life.productsearch.databinding.ActivityProductSearchBinding
import me.life.productsearch.model.ResultData
import me.life.productsearch.ui.adapter.SearchAdapter
import me.life.productsearch.ui.viewmodel.ProductSearchViewModel

@AndroidEntryPoint
class ProductSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductSearchBinding
    private val viewModel: ProductSearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        searchAdapter = SearchAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ProductSearchActivity)
            adapter = searchAdapter
        }

        binding.svProducts.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let {
                    viewModel.searchProducts(it)
                }
                return false
            }

        })

        viewModel.response.observe(this, {
            run {
                when (it) {
                    is ResultData.Success -> {
                        searchAdapter.submitList(null)
                        searchAdapter.submitList(it.data?.data)
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