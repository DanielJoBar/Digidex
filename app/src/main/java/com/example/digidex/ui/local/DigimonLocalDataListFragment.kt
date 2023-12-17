package com.example.digidex.ui.local

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.digidex.databinding.FragmentDigimonListBinding
import com.example.digidex.databinding.FragmentDigimonLocalDataListBinding
import com.example.digidex.ui.adapter.DigimonListAdapter
import com.example.digidex.ui.adapter.LocalDigimonListAdapter

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DigimonLocalDataListFragment : Fragment() {
    lateinit var binding:FragmentDigimonLocalDataListBinding
    val viewModel:DigimonLocalDataListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentDigimonLocalDataListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        val adapter = LocalDigimonListAdapter(requireContext())
        val recyclerView = binding.rvFragmentLocal
        recyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel._staticList.collect{
                    adapter.submitList(it)
                }
            }
        }
        binding.addButton.setOnClickListener{
           val navigation = DigimonLocalDataListFragmentDirections.actionDigimonCreateLocalDataListFragmentToDigimonLocalDataFragment()
            findNavController().navigate(navigation)
        }
    }
}