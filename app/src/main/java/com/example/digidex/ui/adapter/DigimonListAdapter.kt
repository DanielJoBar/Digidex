package com.example.digidex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import com.example.digidex.data.repository.Digimon
import com.example.digidex.databinding.DigimonListItemBinding
import coil.request.ImageRequest


class DigimonListAdapter(private val context: Context):
    ListAdapter<Digimon, DigimonListAdapter.DigimonViewHolder>(DigimonDiffCallback) {

    inner class DigimonViewHolder(private val binding: DigimonListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(digimon:Digimon) {
            binding.digimonText.text = digimon.name;
            val imageRequest = ImageRequest.Builder(context)
                .data(digimon.images)
                .crossfade(true)
                .target(binding.digimonImage)
                .build()
            context.imageLoader.enqueue(imageRequest)
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

    override fun onBindViewHolder(holder: DigimonViewHolder, position: Int) = holder.bind(getItem(position))





}