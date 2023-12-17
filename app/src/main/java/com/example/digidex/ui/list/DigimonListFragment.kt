package com.example.digidex.ui.list

import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.digidex.data.repository.Digimon
import com.example.digidex.databinding.FragmentDigimonListBinding
import com.example.digidex.ui.adapter.DigimonListAdapter
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
        binding = FragmentDigimonListBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isLoading.visibility = View.VISIBLE
        val adapter = DigimonListAdapter(requireContext())
        val recyclerView = binding.rvFragment
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel._staticList.collect{
                    binding.isLoading.visibility = View.GONE
                    adapter.submitList(it)
                }
            }
        }
    }
}