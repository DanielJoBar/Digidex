package com.example.digidex.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.digidex.R
import com.example.digidex.databinding.FragmentDigimonDetailBinding
import com.example.digidex.ui.list.DigimonListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DigimonDetailFragment : Fragment() {
    lateinit var binding: FragmentDigimonDetailBinding
    private val arguments : DigimonDetailFragmentArgs by navArgs()
    private val viewModel: DigimonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDigimonDetailBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments.name
        viewModel.viewModelScope.launch {
            viewModel.getDigimon(name)
        }
        viewModel.viewModelScope.launch {
            viewModel.digimonData.collect(){
                digimon -> binding.digimonText.text = digimon.name
                binding.digimonImage.load(digimon.images)
                binding.digimonLevels.text = digimon.levels
                binding.digimonAttributes.text = digimon.attributes
                binding.digimonTypes.text = digimon.types
                binding.botonNavegacion.setOnClickListener {
                    NavHostFragment.findNavController(binding.botonNavegacion.findFragment()).navigateUp()
                }
            }
        }
    }
}