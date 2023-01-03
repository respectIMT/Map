package imt.respect.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import imt.respect.map.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        val locationNow = LatLng(40.629540371344675, 71.07801400304626 )

        val cameraPosition = CameraPosition.Builder()
        cameraPosition.target(locationNow)
        cameraPosition.bearing(45f)
        cameraPosition.tilt(45f)
        cameraPosition.zoom(15f)

        mMap.addMarker(MarkerOptions().title("in Marker").position(locationNow))
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition.build()))

        mMap.setOnMapClickListener {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }


    }
}