package com.example.llegadasemt.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.llegadasemt.R
import com.example.llegadasemt.adapter.ArrivalsAdapter
import com.example.llegadasemt.data.Hello
import com.example.llegadasemt.databinding.ActivityArrivalsBinding
import com.example.llegadasemt.network.getRequest
import kotlinx.coroutines.launch

class ArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrivalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.arrivalsToolbar)

        binding.arrivalsToolbar.setTitle(binding.arrivalsToolbar.title.toString() + " " + intent.extras?.get("stop"))

        Toast.makeText(this, getString(R.string.arrivals_text), Toast.LENGTH_SHORT).show()
        var auxView = MyView()

        val arrivalsList: RecyclerView = binding.arrivalsList
        val arrivalsAdapter: ArrivalsAdapter = ArrivalsAdapter()
        arrivalsList.adapter = arrivalsAdapter
        auxView.getData("hello", arrivalsAdapter)
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

class MyView: ViewModel() {
    private val _result = MutableLiveData<Hello>()
    private val result: LiveData<Hello> = _result
    fun getData(path: String, adapter: ArrivalsAdapter): Hello? {
        var aux: ArrayList<Hello> = ArrayList<Hello>()
        viewModelScope.launch {
            try {
                _result.value = getRequest().getPath(path)
                println(result.value?.text)
                for (i in 1..20) {
                    result.value?.let { aux.add(it) }
                }
                adapter.arrivals = aux
                //adapter.arrivals = listOf(result.value) as List<Hello>
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
        return result.value
    }
}