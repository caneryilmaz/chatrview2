package com.caner.chartviewtest.viewmodel

import DynamicRepository
import StaticRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tradingview.lightweightcharts.api.series.common.SeriesData
import com.tradingview.lightweightcharts.api.series.enums.SeriesType
import com.tradingview.lightweightcharts.example.app.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RealTimeEmulationViewModel: ViewModel() {

    private val staticRepository = StaticRepository()
    private val dynamicRepository = DynamicRepository()

    val seriesFlow: Flow<SeriesData>
        get() = dynamicRepository.getListSeriesData(data.value!!) {
            loadData()
        }

    val seriesData: LiveData<Data>
        get() = data

    private val data: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>().also {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            val barData = staticRepository.getRealTimeEmulationSeriesData()
            data.postValue(Data(barData, SeriesType.CANDLESTICK))
        }
    }
}