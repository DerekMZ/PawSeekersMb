package com.dk.pawseekers

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.dk.pawseekers.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var lastLocation: Location
    private lateinit var fusedLocationClient: FusedLocationProviderClient       //varaibles del mapa y ubicacion

    val dog = LatLng(28.6616476,-106.0404666)
    val dog2 = LatLng(28.64506774511174, -106.09616975742165)
    val dog3 = LatLng(28.652816276950986, -106.09077481132292)
    val dog4 = LatLng(28.641751839636466, -106.13183150523109)
    val dog5 = LatLng(28.62096033741411, -106.11831066225983)
    val dog6 = LatLng(28.615077452044552, -106.12284829332147)
    val dog7 = LatLng(28.658847007934128, -106.08454225144168)          //variables de ubicacion

    companion object{
        private const val LOCATION_REQUEST_CODE =1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    mapFragment.getMapAsync(this)

        locationArrayList = ArrayList()
        locationArrayList!!.add(dog)
        locationArrayList!!.add(dog2)
        locationArrayList!!.add(dog3)
        locationArrayList!!.add(dog4)
        locationArrayList!!.add(dog5)
        locationArrayList!!.add(dog6)
        locationArrayList!!.add(dog7)       //arraylist de las de las multiples ubicaciones
    }

    private var locationArrayList: ArrayList<LatLng>? = null //la arraylist vacia

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap //mapa para map

        mMap.uiSettings.isZoomControlsEnabled = true //se activo el los botones de zoom
    mMap.setOnMarkerClickListener(this)
        setUpMap()

        for (i in locationArrayList!!.indices){
            mMap.addMarker(MarkerOptions().position(locationArrayList!![i]).title("Demo dog")) //se agregan las ubicaciones que fueron insertadas en la array list


        }

        //val markerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.pawpin_layout,null) //se customiza los pines a unos con tematica de la aplicacion
        //val cardView = markerView.findViewById<CardView>(R.id.pinCustom) //esta en progreso, comentado para que app siga funcioandno

    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
             {

                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLong)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 16f))
            }
        }           //procedimineto para obtener ubicacion en pin y adicionalmente hacer un zoom automatico, esta pin no se actualizara hasta volver a entrar al mapa
    }
    private fun placeMarkerOnMap(currentLatLng: LatLng){
        val markerOptions = MarkerOptions().position(currentLatLng)
        markerOptions.title("$currentLatLng")
        mMap.addMarker(markerOptions)       //pin de la ubicacion del usuario con la lat y lon
    }

    override fun onMarkerClick(p0: Marker) = false
}