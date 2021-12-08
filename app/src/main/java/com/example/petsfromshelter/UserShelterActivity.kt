package com.example.petsfromshelter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.petsfromshelter.fragments.PetsFragment
import com.example.petsfromshelter.fragments.ShelterInformationFragment
import com.example.petsfromshelter.models.Animal
import com.example.petsfromshelter.models.Shelter
import com.example.petsfromshelter.retrofit.objects.GetAnimalsByShelter
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserShelterActivity : AppCompatActivity() {

    private val petsFragment = PetsFragment()
    private val shelterInformationFragment = ShelterInformationFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_shelter)

        val rawData = intent.extras
        val shelter = rawData?.getSerializable("shelter") as Shelter

        val bundle = Bundle()
        bundle.putSerializable("shelter", shelter)

        shelterInformationFragment.arguments = bundle
        replaceFragment(shelterInformationFragment)


        val navigation: BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.pets ->  {
                    val animalService = GetAnimalsByShelter.getAnimalsByShelter
                    animalService.getAnimalsList(shelter.id).enqueue(object : Callback<ArrayList<Animal>> {
                        override fun onFailure(call: Call<ArrayList<Animal>>, t: Throwable) {
                            println("Error")
                            println(t.message)
                        }

                        override fun onResponse(
                                call: Call<ArrayList<Animal>>,
                                response: Response<ArrayList<Animal>>
                        ) {
                            val animals = response.body()!!
                            println(animals)
                            bundle.putInt("shelterId", shelter.id)
                            bundle.putSerializable("animals", animals)
                            petsFragment.arguments = bundle
                            replaceFragment(petsFragment)
                        }
                    })
                }
                R.id.shelter -> {
                    bundle.putSerializable("shelter", shelter)
                    shelterInformationFragment.arguments = bundle
                    replaceFragment(shelterInformationFragment)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}