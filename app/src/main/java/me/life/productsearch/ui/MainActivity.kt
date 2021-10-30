package me.life.productsearch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.life.productsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearchProducts.setOnClickListener {
            startActivity(Intent(this@MainActivity,ProductSearchActivity::class.java))
        }

        binding.btnMyOrders.setOnClickListener {
            startActivity(Intent(this@MainActivity,MyOrdersActivity::class.java))
        }

        binding.btnPrescription.setOnClickListener {
            startActivity(Intent(this@MainActivity,PrescriptionActivity::class.java))
        }
    }
}