package me.life.productsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import me.life.productsearch.databinding.ActivityMyOrdersBinding
import me.life.productsearch.databinding.ActivityPrescriptionBinding

@AndroidEntryPoint
class PrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}