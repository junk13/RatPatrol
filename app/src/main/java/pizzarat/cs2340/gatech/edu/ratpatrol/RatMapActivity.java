package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;

/**
 * Represents the screen where the user can pick different fields about a rat
 * sighting report to generate a Google Maps of those reports that fix their
 * specified fields.
 */
public class RatMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        SQLiteReportBroker report = new SQLiteReportBroker();
        //create array list from reports within the static date range in StaticHelper.
        ArrayList<ReportStructure> reportArray = report.getDateConstrainedReports(getBaseContext());
        populateFromFilter(reportArray);

    }

    /**
     * private helper to populate the map
     *
     * @param reports : a list of all reports to add to the map
     */
    //TODO: use appropiate values latlong.
    private void populateFromFilter(ArrayList<ReportStructure> reports) {
        for (ReportStructure report : reports) {
            Double latitude = 0.;
            Double longitude = 0.;
            try {
                latitude = Double.parseDouble(report.getLatitude());
                longitude = Double.parseDouble(report.getLongitude());
            } catch (Exception e)
            {
                Log.d("hidden",e.getLocalizedMessage());
            }
            LatLng coords = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(coords)
                    .title("Key: " + report.getKey()).snippet(report.mapToString()));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(41, -74)));
    }

}
