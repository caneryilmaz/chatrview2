package com.caner.chartviewtest.viewmodel

import StaticRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tradingview.lightweightcharts.api.series.enums.SeriesType
import com.tradingview.lightweightcharts.example.app.model.Data
import kotlinx.coroutines.launch

class CustomThemesViewModel: ViewModel() {

    private val staticRepository = StaticRepository()

    val seriesData: LiveData<Data>
        get() = data

    private val data: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>().also {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val barData = staticRepository.getCustomThemesSeriesData()
            data.postValue(Data(barData, SeriesType.LINE))
        }
    }
}