package com.example.llegadasemt.main

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.llegadasemt.R
import com.example.llegadasemt.adapter.ArrivalsAdapter
import com.example.llegadasemt.databinding.ActivityArrivalsBinding
import com.example.llegadasemt.network.RequestView


class ArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrivalsBinding
    private lateinit var myAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.arrivalsToolbar)

        val stop: String = intent.extras?.get("stop") as String
        binding.arrivalsToolbar.setTitle(binding.arrivalsToolbar.title.toString() + " " + stop)
        val requests: RequestView = RequestView(intent.extras?.get("token") as String)

        val arrivalsList: RecyclerView = binding.arrivalsList
        val arrivalsAdapter: ArrivalsAdapter = ArrivalsAdapter()
        arrivalsList.adapter = arrivalsAdapter
        requests.getArrivals(stop, arrivalsAdapter, binding)

        binding.floatingRefresh.setOnClickListener {
            binding.floatingRefresh.visibility = View.GONE
            binding.timeout.visibility = View.GONE
            binding.arrivalsList.visibility = View.INVISIBLE
            binding.emptyArrive.visibility = View.VISIBLE
            binding.emptyArrive.startAnimation(myAnimation)
            requests.getArrivals(stop, arrivalsAdapter, binding)
        }
    }

    override fun onStart() {
        super.onStart()
        myAnimation = android.view.animation.AnimationUtils.loadAnimation(applicationContext, R.anim.breath_animation)
        binding.emptyArrive.startAnimation(myAnimation)
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