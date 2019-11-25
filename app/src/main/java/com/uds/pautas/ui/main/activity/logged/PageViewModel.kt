package com.uds.pautas.ui.main.activity.logged

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uds.pautas.util.StatusEnum

class PageViewModel() : ViewModel() {

    private val _index = MutableLiveData<Int>()

    fun setIndex(index: Int) {
        _index.value = index
    }
}