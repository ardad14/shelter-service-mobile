package com.example.petsfromshelter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.petsfromshelter.models.Shelter
import com.example.petsfromshelter.retrofit.objects.GetAllShelters;
import com.example.petsfromshelter.retrofit.objects.UpdateShelter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShelterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter)

        val name: EditText = findViewById<EditText>(R.id.nameText)
        val longitude: EditText = findViewById<EditText>(R.id.longitudeText)
        val latitude: EditText = findViewById<EditText>(R.id.latitudeText)
        val phone: EditText = findViewById<EditText>(R.id.phoneText)
        val email: EditText = findViewById<EditText>(R.id.emailText)
        val siteUrl: EditText = findViewById<EditText>(R.id.urlText)



        val ShelterService = GetAllShelters.sheltersRetrofitService;
        ShelterService.getShelterList().enqueue(object : Callback<ArrayList<Shelter>> {
            override fun onFailure(call: Call<ArrayList<Shelter>>, t: Throwable) {}

            override fun onResponse(call: Call<ArrayList<Shelter>>, response: Response<ArrayList<Shelter>>) {
                var shelter = Shelter(
                        1,
                        "name",
                        "email",
                        4.4,
                        5.5,
                        "phone",
                        "siteUrl"
                );
                response.body()?.forEach {
                    if (it.id == 2) {
                        shelter = it;
                    }
                }

                //println(shelter)

                name.text.insert(0, shelter?.name.toString())
                longitude.text.insert(0, shelter?.longitude.toString())
                latitude.text.insert(0, shelter?.latitude.toString())
                phone.text.insert(0, shelter?.phone.toString())
                email.text.insert(0, shelter?.email.toString())
                siteUrl.text.insert(0, shelter?.siteUrl.toString())
            }
        })
    }

    fun editShelter(view: View) {
        val name: EditText = findViewById<EditText>(R.id.nameText)
        val longitude: EditText = findViewById<EditText>(R.id.longitudeText)
        val latitude: EditText = findViewById<EditText>(R.id.latitudeText)
        val phone: EditText = findViewById<EditText>(R.id.phoneText)
        val email: EditText = findViewById<EditText>(R.id.emailText)
        val siteUrl: EditText = findViewById<EditText>(R.id.urlText)

        val shelter = Shelter(
            1,
            name.text.toString(),
            email.text.toString(),
            longitude.text.toString().toDouble(),
            latitude.text.toString().toDouble(),
            phone.text.toString(),
            siteUrl.text.toString()
        );

        val updateShelter = UpdateShelter.updateShelter;
        updateShelter.updateShelter(shelter).enqueue(object : Callback<Shelter> {
            override fun onFailure(call: Call<Shelter>, t: Throwable) {}

            override fun onResponse(call: Call<Shelter>, response: Response<Shelter>) {

                println(response.code());
            }
        })

        val shelterActivity = Intent(this, ShelterActivity::class.java)
        startActivity(shelterActivity)
    }
}