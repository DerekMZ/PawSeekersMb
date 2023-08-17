package com.dk.pawseekers

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.graphics.set
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
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




    val dog = LatLng(28.6099568, -106.1212242)
    val dog2 = LatLng(28.6180023, -106.1098469)
    val dog3 = LatLng(29.1550849, -111.0035675)
         //variables de ubicacion

    companion object{
        private const val LOCATION_REQUEST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    mapFragment.getMapAsync(this)


    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap //mapa para map

        mMap.uiSettings.isZoomControlsEnabled = true //se activo el los botones de zoom
    mMap.setOnMarkerClickListener(this)
        setUpMap()


            mMap.addMarker(MarkerOptions().position(dog).title("Jupiter")) //por medio de las varaibles dog obtenemos
            mMap.addMarker(MarkerOptions().position(dog2).title("Gato"))
            mMap.addMarker(MarkerOptions().position(dog3).title("Chalino"))






        val markerView = (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.pawpin_layout,null)
        //se customiza los pines a unos con tematica de la aplicacion
        val cardView = markerView.findViewById<CardView>(R.id.markerCustom)
        //mediante el layout pawpin se creo una card donde contiene el pin custom


        //procedimineto donde se agrega en las ubicacion de los perros de la arraylist el pin custom
        val bitmap1 = Bitmap.createScaledBitmap(viewToBitMap(cardView)!!, cardView.width, cardView.height, false)
        val smallMarkerIcon1 = BitmapDescriptorFactory.fromBitmap(bitmap1)
        googleMap.addMarker(MarkerOptions().position(dog).icon(smallMarkerIcon1))

        val bitmap2 = Bitmap.createScaledBitmap(viewToBitMap(cardView)!!, cardView.width, cardView.height, false)
        val smallMarkerIcon2 = BitmapDescriptorFactory.fromBitmap(bitmap2)
        googleMap.addMarker(MarkerOptions().position(dog2).icon(smallMarkerIcon2))

        val bitmap3 = Bitmap.createScaledBitmap(viewToBitMap(cardView)!!, cardView.width, cardView.height, false)
        val smallMarkerIcon3 = BitmapDescriptorFactory.fromBitmap(bitmap3)
        googleMap.addMarker(MarkerOptions().position(dog3).icon(smallMarkerIcon3))



        }



    private fun viewToBitMap(view: View): Bitmap?{
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.layout(0,0,view.measuredWidth, view.measuredHeight)
        view.draw(canvas)
        return bitmap
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