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
import kotlinx.coroutines.launch

class RequestView: ViewModel {
    private val _resultLogin = MutableLiveData<Login>()
    private val resultLogin: LiveData<Login> = _resultLogin

    private val _resultLogout = MutableLiveData<Logout>()
    private val resultLogout: LiveData<Logout> = _resultLogout

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

    fun getArrivals(stop: String, adapter: ArrivalsAdapter, emptyArrive: View) {
        viewModelScope.launch {
            try {
                _resultArrivals.value = getRequest().getArrivalTimes(accessToken, stop, ArriveBody("EN", "Y", "Y", "Y", ""))
                var arrivals = ArrayList<MyArrival>()
                var times = ArrayList<String>()
                lateinit var destination: String
                lateinit var lineNumb: String
                for(line in resultArrivals.value!!.data.get(0).stopData.get(0).lines) {
                    for (arrival in resultArrivals.value!!.data.get(0).arrivals) {
                        if (line.label == arrival.line) {
                            lineNumb = arrival.line
                            times.add((arrival.estimatedTime/60).toString() + " min")
                            destination = arrival.destination
                        }
                    }
                    if (line.label == lineNumb) {
                        arrivals.add(MyArrival(line.label, times[0], times[1], destination, "#" + line.background))
                    }
                    times = ArrayList<String>()
                }
                adapter.arrivals = arrivals
                emptyArrive.visibility = View.GONE
            }
            catch (e: Exception) {
                println(e.message)
            }
        }
    }
}