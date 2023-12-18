package com.example.digidex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import com.example.digidex.data.repository.models.Digimon
import com.example.digidex.databinding.DigimonListItemBinding
import coil.request.ImageRequest
import com.example.digidex.ui.list.DigimonListFragmentDirections


class DigimonListAdapter(private val context: Context):
    ListAdapter<Digimon, DigimonListAdapter.DigimonViewHolder>(DigimonDiffCallback) {

    inner class DigimonViewHolder(private val binding: DigimonListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(digimon: Digimon) {
            binding.digimonName.text = digimon.name
            val imageRequest = ImageRequest.Builder(context)
                .data(digimon.images)
                .crossfade(true)
                .target(binding.digimonImage)
                .build()
            context.imageLoader.enqueue(imageRequest)
            binding.linearGrid.setOnClickListener {
                findNavController(binding.linearGrid.findFragment()).navigate(
                DigimonListFragmentDirections.actionDigimonListFragmentToDigimonDetailFragment(
                    name = digimon.name,
                    Local = false
                )
                )
            }
        }
    }

    private object DigimonDiffCallback: DiffUtil.ItemCallback<Digimon>(){
        override fun areItemsTheSame(oldItem: Digimon, newItem: Digimon) = oldItem.name == newItem.name
        override fun areContentsTheSame(oldItem: Digimon, newItem: Digimon) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonViewHolder =
        DigimonViewHolder(DigimonListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }





}