package com.cbellmont.ejercicioandroid11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.ejercicioandroid11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : PersonajesAdapter
    private val listaPersonajes = loadData()

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerView()

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.rbTodos.id -> adapter.updatePersonajes(listaPersonajes)
                binding.rbBuenos.id -> adapter.updatePersonajes(listaPersonajes.filter { it.esBueno })
                binding.rbMalos.id -> adapter.updatePersonajes(listaPersonajes.filter { !it.esBueno })
            }
        }
    }

    private fun createRecyclerView() {
        adapter = PersonajesAdapter(listaPersonajes)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }


    private fun loadData() : List<Personaje>{
        val personaje1 = Personaje("Aragorn", "Humano", R.mipmap.aragorn, true)
        val personaje2 = Personaje("Gandalf", "Mago", R.mipmap.gandalf, true)
        val personaje3 = Personaje("Boromir", "Humano", R.mipmap.boromir, true)
        val personaje4 = Personaje("Legolas", "Elfo", R.mipmap.legolas, true)
        val personaje5 = Personaje("Orco Feo", "Orco", R.mipmap.orco, false)
        val personaje6 = Personaje("Smagu", "Dragon", R.mipmap.smagu, false)

        return mutableListOf(personaje1,personaje2,personaje3,personaje4,personaje5,personaje6).apply { shuffle() }
    }
}