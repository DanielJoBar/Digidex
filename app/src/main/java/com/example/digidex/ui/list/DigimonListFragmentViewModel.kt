package com.example.digidex.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.data.repository.Digimon
import com.example.digidex.data.repository.DigimonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DigimonListFragmentViewModel  @Inject constructor(private val localRepository:DigimonRepository):ViewModel(){
    private  val _list:MutableStateFlow<List<Digimon>> = MutableStateFlow(listOf())
    val _staticList: StateFlow<List<Digimon>>
        get() {
            return this._list
        }
    init {
        this.viewModelScope.launch {
            try {
                localRepository.updateDatabase()
            }catch (e:Exception){}
        }
        this.viewModelScope.launch {
            localRepository.allDigimon.collect(){
                _list.value = it
            }
        }
    }
}