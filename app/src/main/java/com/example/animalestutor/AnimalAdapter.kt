package com.example.animalestutor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(
    private val animalList: List<Animal>,
    var currentLanguage: MainActivity.Language,
    private val itemClick: (Animal) -> Unit
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAnimal: ImageView = itemView.findViewById(R.id.imgAnimal)
        val txtAnimalName: TextView = itemView.findViewById(R.id.txtAnimalName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.imgAnimal.setImageResource(animal.imagenResId)
        holder.txtAnimalName.text = if (currentLanguage == MainActivity.Language.ES) animal.nombreEs else animal.nombreEn


        val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale_anim)
        holder.itemView.startAnimation(animation)

        holder.itemView.setOnClickListener {
            itemClick(animal)
        }
    }

    override fun getItemCount(): Int = animalList.size
}


