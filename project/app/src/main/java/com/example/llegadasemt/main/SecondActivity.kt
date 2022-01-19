package com.example.llegadasemt.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.llegadasemt.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.secondToolbar)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0,0)
    }

    override fun onSupportNavigateUp(): Boolean {
        val result = super.onSupportNavigateUp()
        overridePendingTransition(0,0)
        return result
    }
}