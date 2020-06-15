package com.example.assignmenttest.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseActivity
import com.example.assignmenttest.databinding.ActivityMainBinding
import com.example.assignmenttest.ui.model.ViewState
import com.example.assignmenttest.ui.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.SphericalUtil

class MapsActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private  val startLocation = LatLng(12.94523, 77.61896)
    private  val endLocation = LatLng(12.95944, 77.66085)
    val LatLongB = LatLngBounds.Builder()

    override fun layoutId(): Int = R.layout.activity_maps
    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestLocationPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1021)

    }

    private fun checkLocationPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1021) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                drawpolyline()
            }
        }
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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        showPredefineMarkerLocation()
        observeData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showPredefineMarkerLocation() {
        mMap.apply {
            addMarker(MarkerOptions().position(startLocation).title("Marker in Sydney"))
            addMarker(MarkerOptions().position(endLocation).title("Marker in Sydney"))
            moveCamera(CameraUpdateFactory.newLatLng(startLocation))
        }

        if(checkLocationPermission()){
            drawpolyline()
        }else requestLocationPermission()
    }

    private fun observeData() {
        viewModel.downloadResponse.observe(this, Observer {
            when(it){
                is ViewState.Error-> Toast.makeText(this,"error occured",Toast.LENGTH_SHORT).show()
                is ViewState.Success->{
                    drawpoly(it.data)
                }
            }
        })
    }

    private fun drawpolyline() {
        // build URL to call API
        val url = getURL(startLocation, endLocation)
        Log.e("url-->",url)
         viewModel.download(url)

    }

    private fun getURL(startLocation: LatLng, endLocation: LatLng): String {
        val origin = "origin=" + startLocation.latitude + "," + startLocation.longitude
        val dest = "destination=" + endLocation.latitude + "," + endLocation.longitude
        //todo provide the key with every request
        val key = "key="+ resources.getString(R.string.google_maps_key)
        val sensor = "sensor=false"
        val params = "$origin&$dest&$sensor&$key"
        return "https://maps.googleapis.com/maps/api/directions/json?$params"
    }

    fun drawpoly(polypts: List<LatLng>){
        val options = PolylineOptions()
        options.apply {
            color(Color.RED)
            width(5f)
            add(startLocation)
        }
        LatLongB.include(startLocation)
        for (point in polypts)  {
            options.add(point)
            LatLongB.include(point)
        }
        options.add(endLocation)
        LatLongB.include(endLocation)
        // build bounds
        val bounds = LatLongB.build()
        mMap.apply {
            addPolyline(options)
            moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
        }

    }

}
