package brunel.csgroup18.squadlink;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvName;
    private TextView tvBio;

    private String profileId;
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePic;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

         tvName = (TextView) view.findViewById(R.id.tvName);
         tvBio = (TextView) view.findViewById(R.id.tvBio);

        String id = MainActivity.getUserid();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Profile Response","Received response");
                try {
                    JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    Log.i("Profile Success",response);

                    firstName = jsonResponse.getString("first_name");
                    lastName = jsonResponse.getString("last_name");
                    bio = jsonResponse.getString("bio");
                    profilePic = jsonResponse.getString("profile_pic");

                    tvName.setText(firstName + " " + lastName);
                    tvBio.setText(bio);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        ProfileRequest profileRequest = new ProfileRequest(id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(profileRequest);

        return view;

    }

}
