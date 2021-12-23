package com.example.petsfromshelter.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsfromshelter.R
import com.example.petsfromshelter.models.Animal
import com.example.petsfromshelter.models.AnimalVaccination
import com.example.petsfromshelter.retrofit.objects.GetVaccination
import com.example.petsfromshelter.retrofit.objects.MakeBook
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList


class CustomRecyclerAdapter(private val animals: ArrayList<Animal>) :
        RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView? = null
        var nameText: TextView? = null
        var descriptionText: TextView? = null
        var imageView: ImageView? = null
        var genderText: TextView? = null
        var ageText: TextView? = null
        var vaccinationText: TextView? = null
        var bookButton: Button? = null
        var bookedText: TextView? = null

        init {
            id = itemView.findViewById(R.id.id)
            nameText = itemView.findViewById(R.id.textName)
            descriptionText = itemView.findViewById(R.id.textDescription)
            imageView = itemView.findViewById(R.id.imageView)
            genderText = itemView.findViewById(R.id.textGender)
            ageText = itemView.findViewById(R.id.textAge)
            vaccinationText = itemView.findViewById(R.id.textVaccination)
            bookButton = itemView.findViewById(R.id.buttonBook)
            bookedText = itemView.findViewById(R.id.textBooked)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.animal_card, parent, false)
        parent.setPadding(50, 50, 50 ,50)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var vaccination: ArrayList<AnimalVaccination> = arrayListOf()
        val animalService = GetVaccination.getVaccinationList
        animalService.getVaccinationList(animals[position].id).enqueue(object : Callback<ArrayList<AnimalVaccination>> {
            override fun onFailure(call: Call<ArrayList<AnimalVaccination>>, t: Throwable) {
                println("Error")
                println(t.message)
            }

            override fun onResponse(
                    call: Call<ArrayList<AnimalVaccination>>,
                    response: Response<ArrayList<AnimalVaccination>>
            ) {
                vaccination = response.body()!!

                holder.id?.text = animals.map { animal -> animal.id }[position].toString()
                holder.nameText?.text = animals.map { animal -> animal.name }[position]
                holder.descriptionText?.text = animals.map { animal -> "Опис: " + animal.description }[position]

                Glide.with(holder.itemView).load( animals.map { animal -> animal.imageUrl }[position]).into(
                        holder.imageView!!
                )

                holder.genderText?.text = animals.map { animal -> "Стать: " + animal.gender }[position]
                holder.ageText?.text = animals.map { animal -> "Вік: " + animal.age }[position]

                if (vaccination.size > 0) {
                    holder.vaccinationText?.text = "Вакцинацiя: " + vaccination[vaccination.size - 1].toString()
                }


                if (animals[position].status.equals("FREE")) {
                    holder.bookButton?.visibility = View.VISIBLE
                    holder.bookedText?.visibility = View.INVISIBLE
                } else {
                    holder.bookButton?.visibility = View.INVISIBLE
                    holder.bookedText?.visibility = View.VISIBLE
                }

                holder.bookButton?.setOnClickListener {
                    val currentAnimal = animals.filter { animal -> animal.id == holder.id?.text.toString().toInt() }[0]
                    currentAnimal.status = "BOOKED"
                    val bookService = MakeBook.makeBook
                    bookService.makeBook(currentAnimal).enqueue(object : Callback<Animal> {
                        override fun onFailure(call: Call<Animal>, t: Throwable) {
                            println("Error")
                            println(t.message)
                        }

                        override fun onResponse(call: Call<Animal>, response: Response<Animal>) {
                            holder.bookButton?.visibility = View.INVISIBLE
                            holder.bookedText?.visibility = View.VISIBLE
                            println(response.body())

                        }
                    })

                }
            }
        })

    }

    override fun getItemCount() = animals.size

    fun DownloadImageFromPath(path: String?): Bitmap? {
        var `in`: InputStream? = null
        var bmp: Bitmap? = null
        var responseCode = -1
        try {
            val url = URL(path)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.setDoInput(true)
            con.connect()
            responseCode = con.getResponseCode()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                `in` = con.getInputStream()
                bmp = BitmapFactory.decodeStream(`in`)
                `in`.close()
               return bmp
            }

        } catch (ex: Exception) {
            Log.e("Exception", ex.toString())
        }
        return bmp
    }
}