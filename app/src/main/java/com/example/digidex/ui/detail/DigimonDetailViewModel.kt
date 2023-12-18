package com.example.digidex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.data.repository.models.Digimon
import com.example.digidex.data.repository.DigimonRepository
import com.example.digidex.data.repository.LocalDigimonRepository
import com.example.digidex.data.repository.models.LocalDigimon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DigimonDetailViewModel @Inject constructor(private val localRepository:DigimonRepository,private val localDigimonRepository: LocalDigimonRepository):ViewModel(){
    private var _digimonData:MutableStateFlow<Digimon> = MutableStateFlow(Digimon(-1,"","","","",""))
    private var _localDigimonData:MutableStateFlow<LocalDigimon> = MutableStateFlow(LocalDigimon("","","",""))
    val digimonData:StateFlow<Digimon>
        get(){
            return this._digimonData
        }
    val localDigimonData:StateFlow<LocalDigimon>
        get() {
            return this._localDigimonData
        }
    suspend fun  getDigimon(name:String){
        viewModelScope.launch {
            val data = localRepository.getOneFromDatabase(name).asDigimon()
            _digimonData.value = data
        }
    }
    suspend fun getLocalDigimon(name:String){
        viewModelScope.launch{
            val data = localDigimonRepository.getOneFromDatabase(name)
            _localDigimonData.value = data.asLocalDigimon()
        }
    }
}