package com.example.petsfromshelter

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.petsfromshelter.google.CustomInfoWindowForGoogleMap
import com.example.petsfromshelter.models.Shelter
import com.example.petsfromshelter.retrofit.objects.GetAllShelters
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShelterMaps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        var shelters = arrayListOf<Shelter>();
        val context = this;

        val ShelterService = GetAllShelters.sheltersRetrofitService;
        ShelterService.getShelterList().enqueue(object : Callback<ArrayList<Shelter>> {
            override fun onFailure(call: Call<ArrayList<Shelter>>, t: Throwable) {}

            override fun onResponse(
                call: Call<ArrayList<Shelter>>,
                response: Response<ArrayList<Shelter>>
            ) {
                shelters = response.body()!!

                for (shelter in shelters) {
                    map.addMarker(
                        MarkerOptions()
                            .position(LatLng(shelter.latitude, shelter.longitude))
                            .title(shelter.name)
                            .snippet(shelter.phone)
                    )
                }

                val homeLatLng = LatLng(49.996143532670125, 36.23116036460851)
                val zoomLevel = 13f
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                map.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context))

                map.setOnInfoWindowClickListener(OnInfoWindowClickListener { marker ->
                    val i = Intent(context, AdminShelterActivity::class.java)
                    startActivity(i)
                })

            }
        })

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        map.isMyLocationEnabled = true


    }
}