package com.example.llegadasemt.main

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.llegadasemt.databinding.ActivityMainBinding
import com.example.llegadasemt.network.DataResponse
import com.example.llegadasemt.network.getRequest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Toast.makeText(this, "El botón funciona correctamente", Toast.LENGTH_SHORT).show()
            binding.textView.text = "Second text"
            var aux = MyView().getData("hello", binding.textView2)
            binding.textView2.text = aux?.text
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            this.overridePendingTransition(0,0)
        }
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