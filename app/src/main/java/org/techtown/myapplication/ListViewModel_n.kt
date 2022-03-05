package org.techtown.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel_n : ViewModel() {
    private val repo = Repo_n()

    fun fetchData(): LiveData<MutableList<Data_noti>> {

        val mutableData = MutableLiveData<MutableList<Data_noti>>()

        repo.getData().observeForever{
            mutableData.value = it
        }

        return mutableData
    }
}