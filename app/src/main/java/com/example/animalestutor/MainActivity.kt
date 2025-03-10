package com.example.animalestutor

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    enum class Language {
        EN, ES
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalAdapter
    private var currentLanguage: Language = Language.ES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerAnimales)
        recyclerView.layoutManager = GridLayoutManager(this, 2)


        val animalList = getAnimalList()

        adapter = AnimalAdapter(animalList, currentLanguage) { animal ->

            if (currentLanguage == Language.ES) {
                playAudio(animal.audioEsResId)
            } else {
                playAudio(animal.audioEnResId)
            }
        }
        recyclerView.adapter = adapter


        val btnToggle: Button = findViewById(R.id.btnToggleLanguage)
        btnToggle.setOnClickListener {
            currentLanguage = if (currentLanguage == Language.ES) Language.EN else Language.ES
            adapter.currentLanguage = currentLanguage
            adapter.notifyDataSetChanged()
            btnToggle.text = if (currentLanguage == Language.ES) "Cambiar a Ingles" else "Cambiar a Espanol"
        }
    }

    private fun playAudio(audioResId: Int) {
        val mediaPlayer = MediaPlayer.create(this, audioResId)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }

    private fun getAnimalList(): List<Animal> {
        return listOf(
            Animal("Perro", "Dog", R.drawable.animal_perro, R.raw.audio_dog_en, R.raw.audio_perro_es),
            Animal("Gato", "Cat", R.drawable.animal_gato, R.raw.audio_cat_en, R.raw.audio_gato_es),
            Animal("Vaca", "Cow", R.drawable.animal_vaca, R.raw.audio_cow_en, R.raw.audio_vaca_es),
            Animal("Caballo", "Horse", R.drawable.animal_caballo, R.raw.audio_horse_en, R.raw.audio_caballo_es),
            Animal("Oveja", "Sheep", R.drawable.animal_oveja, R.raw.audio_sheep_en, R.raw.audio_oveja_es),
            Animal("Cerdo", "Pig", R.drawable.animal_cerdo, R.raw.audio_pig_en, R.raw.audio_cerdo_es),
            Animal("Pajaro", "Bird", R.drawable.animal_pajaro, R.raw.audio_bird_en, R.raw.audio_pajaro_es),
            Animal("Leon", "Lion", R.drawable.animal_leon, R.raw.audio_lion_en, R.raw.audio_leon_es),
            Animal("Elefante", "Elephant", R.drawable.animal_elefante, R.raw.audio_elephant_en, R.raw.audio_elefante_es),
            Animal("Mono", "Monkey", R.drawable.animal_mono, R.raw.audio_monkey_en, R.raw.audio_mono_es),
            Animal("Zorro", "Fox", R.drawable.animal_zorro, R.raw.audio_fox_en, R.raw.audio_zorro_es),
            Animal("Tigre", "Tiger", R.drawable.animal_tigre, R.raw.audio_tiger_en, R.raw.audio_tigre_es),
            Animal("Conejo", "Rabbit", R.drawable.animal_conejo, R.raw.audio_rabbit_en, R.raw.audio_conejo_es),
            Animal("Pez", "Fish", R.drawable.animal_pez, R.raw.audio_fish_en, R.raw.audio_pez_es),
          //  Animal("Pato", "Duck", R.drawable.animal_pato, R.raw.audio_duck_en, R.raw.audio_pato_es)
        )
    }
}


