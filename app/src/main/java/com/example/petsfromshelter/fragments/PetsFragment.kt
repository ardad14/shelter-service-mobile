package com.example.petsfromshelter.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsfromshelter.R
import com.example.petsfromshelter.models.Animal
import com.example.petsfromshelter.retrofit.objects.GetAnimalsByShelter
import com.example.petsfromshelter.services.CustomRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [PetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetsFragment : Fragment() {
    private var animals: ArrayList<Animal>? = null
    private var shelterId: Int? = null

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
     }

    @SuppressLint("ResourceType")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            animals = it.getSerializable("animals") as ArrayList<Animal>?
            shelterId = it.getInt("shelterId")
        }
        val context = activity;
        val view = inflater.inflate(R.layout.fragment_pets, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CustomRecyclerAdapter(animals!!)

        val sortingSpinner = view.findViewById<Spinner>(R.id.spinnerSorting)
        val speciesFiltrationSpinner = view.findViewById<Spinner>(R.id.spinnerSpecies)
        val sterilizedCheckBox = view.findViewById<CheckBox>(R.id.checkBoxSterilized)

        sortingSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        itemSelected: View, selectedItemPosition: Int, selectedId: Long) {

                val choose = resources.getStringArray(R.array.sorting)

                val animalService = GetAnimalsByShelter.getAnimalsByShelter
                animalService.getAnimalsList(shelterId!!).enqueue(object : Callback<ArrayList<Animal>> {
                    override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                        println("Error")
                        println(t.message)
                    }

                    override fun onResponse(
                            call: Call<ArrayList<Animal>>,
                            response: Response<ArrayList<Animal>>
                    ) {
                        animals = response.body()!!
                        animals = when (choose[selectedItemPosition]) {
                            "За ім`ям" -> { animals!!.sortedBy { it.name  }.toCollection(ArrayList<Animal>())}
                            "За віком" -> { animals!!.sortedBy { it.age  }.toCollection(ArrayList<Animal>())}
                            "Хлопчики" -> { animals!!.filter{ animal -> animal.gender.equals("Хлопчик") }.toCollection(ArrayList<Animal>())}
                            "Дівчинки" -> { animals!!.filter{ animal -> animal.gender.equals("Дiвчинка") }.toCollection(ArrayList<Animal>())}
                            else -> animals
                        }
                        recyclerView.adapter = CustomRecyclerAdapter(animals!!)
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        speciesFiltrationSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        itemSelected: View, selectedItemPosition: Int, selectedId: Long) {

                val choose = resources.getStringArray(R.array.species)

                val animalService = GetAnimalsByShelter.getAnimalsByShelter
                animalService.getAnimalsList(shelterId!!).enqueue(object : Callback<ArrayList<Animal>> {
                    override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                        println("Error")
                        println(t.message)
                    }

                    override fun onResponse(
                            call: Call<ArrayList<Animal>>,
                            response: Response<ArrayList<Animal>>
                    ) {
                        animals = response.body()!!
                        animals = when (choose[selectedItemPosition]) {
                            "Собака" -> { animals!!.filter{ animal -> animal.species.equals("Собака") }.toCollection(ArrayList<Animal>()) }
                            "Кіт" -> { animals!!.filter{ animal -> animal.species.equals("Кіт") }.toCollection(ArrayList<Animal>())}
                            else -> animals
                        }
                        recyclerView.adapter = CustomRecyclerAdapter(animals!!)
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        sterilizedCheckBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            val animalService = GetAnimalsByShelter.getAnimalsByShelter
            animalService.getAnimalsList(shelterId!!).enqueue(object : Callback<ArrayList<Animal>> {
                override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                    println("Error")
                    println(t.message)
                }

                override fun onResponse(
                        call: Call<ArrayList<Animal>>,
                        response: Response<ArrayList<Animal>>
                ) {
                    animals = response.body()!!
                    animals = when (isChecked) {
                        true -> { animals!!.filter{ animal -> animal.sterelized }.toCollection(ArrayList<Animal>()) }
                        false -> { animals!!.filter{ animal -> !animal.sterelized }.toCollection(ArrayList<Animal>())}

                    }
                    recyclerView.adapter = CustomRecyclerAdapter(animals!!)
                }
            })
        }

        return view
    }

}