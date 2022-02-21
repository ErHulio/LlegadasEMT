package com.example.llegadasemt.network

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.llegadasemt.adapter.ArrivalsAdapter
import com.example.llegadasemt.data.Arrivals
import com.example.llegadasemt.data.Login
import com.example.llegadasemt.data.Logout
import com.example.llegadasemt.data.MyArrival
import com.example.llegadasemt.databinding.ActivityArrivalsBinding
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch

class RequestView: ViewModel {
    private val _resultLogin = MutableLiveData<Login>()
    private val resultLogin: LiveData<Login> = _resultLogin

    private val _resultLogout = MutableLiveData<Logout>()

    private val _resultArrivals = MutableLiveData<Arrivals>()
    private val resultArrivals: LiveData<Arrivals> = _resultArrivals

    private var accessToken: String = ""

    constructor()

    constructor(token: String) {
        accessToken = token
    }

    fun login(tokenWrapper: Token) {
        viewModelScope.launch {
            try {
                _resultLogin.value = getRequest().login()
                accessToken = resultLogin.value!!.data.get(0).token
                tokenWrapper.token = accessToken
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                _resultLogout.value = getRequest().logout(accessToken)
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun getArrivals(stop: String, adapter: ArrivalsAdapter, binding: ActivityArrivalsBinding) {
        viewModelScope.launch {
            try {
                _resultArrivals.value = getRequest().getArrivalTimes(accessToken, stop, ArriveBody("EN", "Y", "Y", "Y", ""))
                try {
                    val arrivals = ArrayList<MyArrival>()
                    var times = ArrayList<String>()
                    var timesInt = ArrayList<Int>()
                    lateinit var destination: String
                    var lineNumb: String = ""
                    for(line in resultArrivals.value!!.data.get(0).stopData.get(0).lines) {
                        for (arrival in resultArrivals.value!!.data.get(0).arrivals) {
                            if (line.label == arrival.line) {
                                lineNumb = arrival.line
                                timesInt.add(arrival.estimatedTime)
                                times.add(if(arrival.estimatedTime/60 < 60) (arrival.estimatedTime/60).toString() + " min" else "+1 h")
                                destination = arrival.destination
                            }
                        }
                        if (line.label == lineNumb) {
                            arrivals.add(MyArrival(line.label, timesInt[0], times[0], if(times.size == 2) times[1] else "-", destination, "#" + line.background, if(line.foreground != null) "#" + line.foreground else "#FFFFFF"))
                        }
                        times = ArrayList<String>()
                        timesInt = ArrayList<Int>()
                    }
                    adapter.arrivals = arrivals.sortedBy { it.timeInt }
                    binding.emptyArrive.clearAnimation()
                    binding.emptyArrive.visibility = View.GONE
                    if(arrivals.isEmpty()) {
                        binding.noArrivals.visibility = View.VISIBLE
                    }
                    else {
                        binding.arrivalsList.visibility = View.VISIBLE
                        binding.floatingRefresh.visibility = View.VISIBLE
                    }
                }
                catch (e: Exception) {
                    println(e.message)
                    e.printStackTrace()
                }
            }
            catch (e: JsonSyntaxException) {
                binding.emptyArrive.clearAnimation()
                binding.emptyArrive.visibility = View.GONE
                binding.wrongStop.visibility = View.VISIBLE
            }
            catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
            }
        }
    }
}