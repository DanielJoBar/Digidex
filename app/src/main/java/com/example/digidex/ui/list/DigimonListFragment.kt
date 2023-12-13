package com.example.digidex.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.digidex.R
import com.example.digidex.data.repository.Digimon
import com.example.digidex.databinding.FragmentDigimonListBinding
import com.example.digidex.ui.adapter.DigimonAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DigimonListFragment : Fragment() {
    lateinit var binding:FragmentDigimonListBinding
    val viewModel:DigimonListFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isLoading.visibility = View.VISIBLE
        val observer = Observer<List<Digimon>>{
            list -> val adapter = DigimonAdapter(list)
            binding.rvFragment.adapter = adapter
            binding.isLoading.visibility = View.GONE
        }
    }
}