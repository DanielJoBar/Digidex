package com.example.digidex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.digidex.data.repository.models.Digimon
import com.example.digidex.data.repository.models.LocalDigimon
import com.example.digidex.databinding.DigimonListItemBinding
import com.example.digidex.ui.list.DigimonListFragmentDirections
import com.example.digidex.ui.local.DigimonLocalDataListFragmentDirections

class LocalDigimonListAdapter(context: Context):
    ListAdapter<LocalDigimon, LocalDigimonListAdapter.LocalDigimonViewHolder>(DigimonDiffCallback) {

    inner class LocalDigimonViewHolder(private val binding: DigimonListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(digimon: LocalDigimon) {
            binding.digimonText.text = digimon.name
            binding.linearGrid.setOnClickListener {
                NavHostFragment.findNavController(binding.linearGrid.findFragment()).navigate(
                    DigimonLocalDataListFragmentDirections.actionDigimonCreateLocalDataListFragmentToDigimonDetailFragment2(
                        name = digimon.name
                    )
                )
            }
        }
    }

    private object DigimonDiffCallback: DiffUtil.ItemCallback<LocalDigimon>(){
        override fun areItemsTheSame(oldItem: LocalDigimon, newItem: LocalDigimon) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: LocalDigimon, newItem: LocalDigimon) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalDigimonViewHolder =
        LocalDigimonViewHolder(
            DigimonListItemBinding.inflate(
                LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: LocalDigimonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}