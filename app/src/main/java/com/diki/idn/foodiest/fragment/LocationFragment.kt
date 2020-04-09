package com.diki.idn.foodiest.fragment


import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.diki.idn.foodiest.R
import com.diki.idn.foodiest.fragment.LocationFragment.Companion.RQ_LOCATION_PERMISSION
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment(), OnMapReadyCallback {
    private val TAG = this.javaClass.simpleName

    private lateinit var mapView: GoogleMap

    companion object {
        var mapFragment: SupportMapFragment? = null

        //konstanta
        const val RQ_LOCATION_PERMISSION = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)
        mapFragment =
            childFragmentManager.findFragmentById(R.id.fragment_location) as SupportMapFragment

        mapFragment?.getMapAsync(this)
        return view
    }

    override fun onMapReady(p0: GoogleMap?) {
        mapView = p0!!
        val idn = LatLng(-6.174760, 106.827070)
        mapView.addMarker(
                MarkerOptions().position(idn).title("IDN Akhwat").icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.home)
                )
            )
            .showInfoWindow()

        //biar ngezoom nya gk banyak banget
        mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(idn, 16.0f))
        //munculin macet
        mapView.isTrafficEnabled = true
        //nambahin marker
        setMapClick(mapView)
        setMapLongClick(mapView)
        enabledMyLocation()
    }

    private fun enabledMyLocation() {
        if (isPermissionGranted()) {
            mapView.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity()
                , arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
                , RQ_LOCATION_PERMISSION
            )
        }
    }

    //cek permission diizinkan
    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    }

    private fun setMapLongClick(mapView: GoogleMap) {
        mapView.setOnMapLongClickListener {
            val snippets = "${it.latitude.toFloat()} ${it.longitude.toFloat()}"
            mapView.addMarker(
                    MarkerOptions().position(it)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.home))
                        .snippet(snippets)
                        .title("Pinned")
                )
                .showInfoWindow()
        }
    }

    private fun setMapClick(mapView: GoogleMap) {
        mapView.setOnPoiClickListener {
            val poiMarker = mapView.addMarker(
                MarkerOptions().position(it.latLng)
                    .title(it.name)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.home))
                    .snippet(it.placeId)
            )
            poiMarker.showInfoWindow()
        }

    }

    //kaitan dengan permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RQ_LOCATION_PERMISSION) {

            if (grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                enabledMyLocation()
        }
    }
}