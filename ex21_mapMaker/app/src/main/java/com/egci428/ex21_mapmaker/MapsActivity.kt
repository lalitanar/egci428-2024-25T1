package com.egci428.ex21_mapmaker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.egci428.ex21_mapmaker.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var countMarker: Int = 0
    private var preLatLon = LatLng(0.0,0.0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        mMap = googleMap

        //Add a marker in Sydney and move the camera
        /*val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,9F))*/

        mMap.setOnMapClickListener {

            latlon ->
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlon,5F))
            mMap.addMarker(MarkerOptions()
                .position(latlon)
                .title("lat:"+latlon.latitude.toString()+" lon: "+latlon.longitude.toString())
                .snippet(countMarker.toString()))

                if (countMarker>0) {
                    mMap.addPolyline(PolylineOptions()
                        .add(preLatLon, latlon)
                        .width(5F)
                        .color(Color.RED)
                    )
                }
                preLatLon = latlon
                countMarker++
        }



        /*mMap.setOnMapLongClickListener {
            latlon -> mMap.addMarker(MarkerOptions()
                .position(latlon)
            //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.image10101))
                .title("lat:"+latlon.latitude.toString()+" lon: "+latlon.longitude.toString())
                .snippet("egci428")
            )
        }*/

        /*mMap.addPolyline(PolylineOptions()
            .add(LatLng(15.0, 100.45), LatLng(15.432, 100.456), LatLng(15.5, 100.7))
            .width(5F)
            .color(Color.RED)
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(15.0, 100.45),9F))*/

        /*mMap.addPolygon(
            PolygonOptions()
            .add(LatLng(15.0, 100.45), LatLng(15.432, 100.456), LatLng(15.5, 100.7), LatLng(15.0, 100.45))
            .strokeColor(Color.BLUE)
            .fillColor(Color.BLUE)
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(15.0, 100.45),9F))*/

    }
}