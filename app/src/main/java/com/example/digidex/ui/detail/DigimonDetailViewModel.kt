package com.example.digidex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digidex.data.repository.Digimon
import com.example.digidex.data.repository.DigimonRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DigimonDetailViewModel @Inject constructor(private val localRepository:DigimonRepository):ViewModel(){
    private var _digimonData:MutableStateFlow<Digimon> = MutableStateFlow(Digimon(-1,"","","","",""))
    val digimonData:StateFlow<Digimon>
        get(){
            return this._digimonData
        }
    suspend fun  getDigimon(name:String){
        viewModelScope.launch {
            val data = localRepository.getOneFromDatabase(name).asDigimon()
            _digimonData.value = data
        }
    }
}