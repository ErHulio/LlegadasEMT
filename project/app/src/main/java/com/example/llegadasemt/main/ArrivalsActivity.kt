package com.example.llegadasemt.main

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.llegadasemt.R
import com.example.llegadasemt.databinding.ActivityArrivalsBinding
import com.example.llegadasemt.network.DataResponse
import com.example.llegadasemt.network.getRequest
import kotlinx.coroutines.launch

class ArrivalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArrivalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArrivalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.arrivalsToolbar)

        Toast.makeText(this, getString(R.string.arrivals_text), Toast.LENGTH_SHORT).show()
        var auxView = MyView()
        auxView.getData("hello", binding.textViewArrivals)
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
    private val _result = MutableLiveData<DataResponse>()
    private val result: LiveData<DataResponse> = _result
    fun getData(path: String, view: TextView): DataResponse? {
        viewModelScope.launch {
            try {
                _result.value = getRequest().getPath(path)
                println(result.value?.text)
                view.text = result.value?.text
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
        return result.value
    }
}