package brunel.csgroup18.squadlink;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class TeamFinderFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap map;
    MapView mapView;
    View mView;
    EditText etSearch;
    Button btnSearch;

    private String team_id;
    private final static int   MY_PERMISSION_FINE_LOCATION=101;

    public TeamFinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_team_finder, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) mView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);


        etSearch = (EditText) mView.findViewById(R.id.postcode_search);
        btnSearch = (Button) mView.findViewById(R.id.search_button);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String searchTerm = etSearch.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Map Response","Received response");
                        try {

                            Log.i("JSON",response);
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                            String postcode = jsonResponse.getString("postcode");
                            team_id = jsonResponse.getString("team_id");
                            Log.i("postcode",postcode);
                            List<Address> addressList = null;
                            Geocoder geocoder = new Geocoder(getContext());

                            try {
                                addressList = geocoder.getFromLocationName(postcode,1);
                                Address address = addressList.get(0);
                                LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                                placeJoinMarker(latLng,searchTerm);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                MapRequest mapRequest = new MapRequest(searchTerm, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(mapRequest);

            }
        });

    }

    private void placeJoinMarker(LatLng markerPosition, String searchTerm){

        map.addMarker(new MarkerOptions().position(markerPosition).title("Team: "+searchTerm).snippet("Click this box to join team"));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setOnInfoWindowClickListener(this);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        }
        
        else{
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_FINE_LOCATION);
        }
        
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case(MY_PERMISSION_FINE_LOCATION):{

                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        map.setMyLocationEnabled(true);
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                    }
                }
                else{
                    Log.i("Location Permission","Permission Not Granted");
                }

                break;
            }
        }

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Join Team Response","Received response");
                try {

                    Log.i("JSON",response);
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        JoinTeamRequest joinRequest = new JoinTeamRequest(MainActivity.getUserid(), team_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(joinRequest);

        Toast.makeText(getContext(),"Window clicked, nice one",Toast.LENGTH_SHORT).show();
    }
}
