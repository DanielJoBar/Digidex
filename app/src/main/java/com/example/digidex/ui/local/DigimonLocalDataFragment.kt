package com.example.digidex.ui.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.digidex.R
import com.example.digidex.data.repository.models.LocalDigimon
import com.example.digidex.databinding.FragmentDigimonLocalDataBinding
import com.example.digidex.ui.adapter.LocalDigimonListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DigimonLocalDataFragment : Fragment() {
    lateinit var binding: FragmentDigimonLocalDataBinding
    val viewModel:DigimonLocalDataListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDigimonLocalDataBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnCrear.setOnClickListener {
            val digimonName = binding.digimonName.text.toString()
            val digimonLevel = binding.level.text.toString()
            val digimonAttribute = binding.attribute.text.toString()
            val digimonType = binding.type.text.toString()

            if (digimonName.isNotBlank() && digimonLevel.isNotBlank() &&
                digimonAttribute.isNotBlank() && digimonType.isNotBlank()
            ) {
                viewModel.viewModelScope.launch {
                    viewModel.insertDigimonIntoLocal(
                        LocalDigimon(
                            digimonName, digimonLevel, digimonType, digimonAttribute
                        )
                    )
                    findNavController().navigateUp()
                }
            } else {
                val toastMessage = getString(R.string.toastCreateLocalData)
                Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
            }
        }

        binding.botonNavegacion.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}