package com.hadroy.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hadroy.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        rvHeroes = findViewById(R.id.rv_heroes)
        binding.rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                binding.rvHeroes.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                binding.rvHeroes.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(
            this,
            "Kamu Memilih ${hero.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)

        val listHeroes = ArrayList<Hero>()

        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
            listHeroes.add(hero)
        }

        return listHeroes
    }

    private fun showRecyclerList() {
        binding.rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroesAdapter = ListHeroAdapter(list)
        binding.rvHeroes.adapter = listHeroesAdapter

        listHeroesAdapter.setOnItemClickCallback(object: ListHeroAdapter.OnItemClickCallback {
            override fun OnItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }
}