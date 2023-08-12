package com.example.plantapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.Model.FlowerRepository
import com.example.plantapp.Model.Local.Database.FlowerDataBase
import com.example.plantapp.Model.Local.Entities.FlowerDetails
import com.example.plantapp.Model.Local.Entities.FlowerList
import kotlinx.coroutines.launch

class FlowersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FlowerRepository
    private var idSelected: Int = 0

    init {
        val bd = FlowerDataBase.getdatabase(application)
        val Dao = bd.getFlowerDao()
        repository = FlowerRepository(Dao)
        viewModelScope.launch {
            repository.fetchList()
        }


    }

    fun getFlowersList(): LiveData<List<FlowerList>> = repository.flowerListLiveData


    fun getFlowersDetailsByIdFromInternet(id: Int) = viewModelScope.launch {
        idSelected = id
        repository.fetchFlowerDetails(id)

    }

    fun getFlowersDetail(): LiveData<FlowerDetails> = repository.getFlowersDetailsById(idSelected)
}