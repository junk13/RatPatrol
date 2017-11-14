package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteReportBroker;
import pizzarat.cs2340.gatech.edu.structure.ReportStructure;
import pizzarat.cs2340.gatech.edu.structure.StaticHolder;

/**
 * Represents the screen where the user can pick different fields about a rat
 * sighting report to generate a Google Maps of those reports that fix their
 * specified fields.
 */
public class MapActivity extends FragmentActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map
        // is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        SQLiteReportBroker report = new SQLiteReportBroker();
        //create array list from reports within the static date range
        // in StaticHelper.
        ArrayList<ReportStructure> reportArray =
                report.getDateConstrainedReports(getBaseContext());
        populateFromFilter(reportArray);

    }

    /**
     * private helper to populate the map
     *
     * @param reports : a list of all reports to add to the map
     */
    private void populateFromFilter(ArrayList<ReportStructure> reports) {
        for (final ReportStructure report : reports) {
            Double latitude = 0.;
            Double longitude = 0.;
            try {
                latitude = Double.parseDouble(report.getLatitude());
                longitude = Double.parseDouble(report.getLongitude());
            } catch (Exception e)
            {
                Log.d("hidden",e.getLocalizedMessage());
            }
            LatLng coordinates = new LatLng(latitude, longitude);
            Marker marker =
                    mMap.addMarker(new MarkerOptions().position(coordinates)
                            .title("Key: " + report.getKey())
                            .snippet(report.mapToString()));

            // Sets the listener of each rat report to display its details
            // when clicked
            mMap.setOnMarkerClickListener(
                    new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    StaticHolder.report = report;
                    Intent switchToDetailedReport =
                            new Intent(MapActivity.this,
                            DetailedReportViewActivity.class);
                    MapActivity.this.startActivity(switchToDetailedReport);
                    return true;
                }
            });


        }
        LatLng newYork = new LatLng(41, -74);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newYork));
    }

}
