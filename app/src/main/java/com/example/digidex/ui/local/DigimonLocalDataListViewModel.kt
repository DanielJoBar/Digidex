package com.example.digidex.ui.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.data.repository.LocalDigimonRepository
import com.example.digidex.data.repository.models.LocalDigimon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DigimonLocalDataListViewModel @Inject constructor(private val localRepository: LocalDigimonRepository):
    ViewModel(){
    private  val _list: MutableStateFlow<List<LocalDigimon?>> = MutableStateFlow(listOf())
    val _staticList: StateFlow<List<LocalDigimon?>>
        get() {
            return this._list
        }
    init {
        this.viewModelScope.launch {
            localRepository.allLocalDigimon.collect(){
                _list.value = it
            }
        }
    }
    fun insertDigimonIntoLocal(digimon:LocalDigimon){
        viewModelScope.launch{
            localRepository.updateDatabase(digimon)
        }
    }
}