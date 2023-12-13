package com.example.digidex.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.digidex.data.repository.Digimon
import com.example.digidex.databinding.DigimonListItemBinding


class DigimonAdapter(private var digimon : List<Digimon> ):
    RecyclerView.Adapter<DigimonAdapter.DigimonItemViewHolder>() {
    private lateinit var binding: DigimonListItemBinding
    inner class DigimonItemViewHolder(private val binding: DigimonListItemBinding):
    RecyclerView.ViewHolder(binding.root){
        val name:TextView = binding.itemText;
        val image:ImageView = binding.itemImage;
        val grid:GridLayout = binding.itemGrid;
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigimonItemViewHolder {
        val binding = DigimonListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return DigimonItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.digimon.size
    }

    override fun onBindViewHolder(holder: DigimonItemViewHolder, position: Int) {
        val digimonSelected = digimon[position]
        Log.d("TESTING","${digimonSelected.name}")
        holder.name.text = digimonSelected.name
        val url = digimonSelected.images
        holder.image.load(url)
        holder.grid.setOnClickListener(){
            findNavController(holder.itemView).graph
        }
    }




}