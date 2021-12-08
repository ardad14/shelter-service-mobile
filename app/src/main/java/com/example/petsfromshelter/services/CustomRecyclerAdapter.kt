package com.example.petsfromshelter.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.petsfromshelter.R
import com.example.petsfromshelter.models.Animal
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList


class CustomRecyclerAdapter(private val animals: ArrayList<Animal>) :
        RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameText: TextView? = null
        var descriptionText: TextView? = null
        var imageView: ImageView? = null
        var genderText: TextView? = null
        var ageText: TextView? = null

        init {
            nameText = itemView.findViewById(R.id.textName)
            descriptionText = itemView.findViewById(R.id.textDescription)
            imageView = itemView.findViewById(R.id.imageView)
            genderText = itemView.findViewById(R.id.textGender)
            ageText = itemView.findViewById(R.id.textAge)
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
        holder.nameText?.text = animals.map { animal -> animal.name }[position]
        holder.descriptionText?.text = animals.map { animal -> "Опис: " + animal.description }[position]

        Glide.with(holder.itemView).load( animals.map { animal -> animal.imageUrl }[position]).into(
            holder.imageView!!
        )
        holder.genderText?.text = animals.map { animal -> "Стать: " + animal.gender }[position]
        holder.ageText?.text = animals.map { animal -> "Вік: " + animal.age }[position]
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