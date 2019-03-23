package com.pokeapi.lpiem.pokeapiandroid.view


/*
class LocalizationActivity : AppCompatActivity(), MapFragment.OnFragmentInteractionListener, LocationListener {
    private lateinit var latituteField: TextView
    private lateinit var longitudeField: TextView
    private lateinit var detailsfield: TextView
    private lateinit var locationManager: LocationManager
    private lateinit var provider: String
    private lateinit var providers: List<String>
    private var lat: Double = 0.toDouble()
    private var lon: Double = 0.toDouble()
    override fun onFragmentInteraction(uri: Uri) {

    }

    lateinit var mMapFragment:MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localization)
        latituteField = findViewById(R.id.latitude) as TextView
        longitudeField = findViewById(R.id.longitude) as TextView

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //val criteria = Criteria()
        providers = locationManager!!.allProviders
        //provider = locationManager.getBestProvider(criteria, false);
        provider = "network"

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMapFragment  = MapFragment.newInstance(10.0,10.0)
            addFragment()
            return
        }
        val location = locationManager!!.getLastKnownLocation(provider)

        // Initialize the location fields
        if (location != null) {
            println("Provider $provider has been selected.")
            onLocationChanged(location)
        } else {
            latituteField!!.text = "Location not available"
            longitudeField!!.text = "Location not available"
        }





        mMapFragment  = MapFragment.newInstance(lat,lon)
        addFragment()
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager!!.requestLocationUpdates(provider, 400, 1f, this)
    }

    override fun onPause() {
        super.onPause()
        locationManager!!.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        lat = location.latitude
        lon = location.longitude
        latituteField!!.text = "latitude: " + lat.toString()
        longitudeField!!.text = "longitude: " +lon.toString()
        //val detailgetter = DetailsGetter()
        // detailsfield!!.text = detailgetter.getData()
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // TODO Auto-generated method stub

    }

    override fun onProviderEnabled(provider: String) {
        Toast.makeText(this, "Enabled new provider $provider",
                Toast.LENGTH_SHORT).show()

    }

    override fun onProviderDisabled(provider: String) {
        Toast.makeText(this, "Disabled provider $provider",
                Toast.LENGTH_SHORT).show()
    }



    fun addFragment() {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.add(R.id.frgContainer, mMapFragment)
        ft.commit()
    }


}
*/