package brunel.csgroup18.squadlink;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan Hoy on 24/01/2018.
 */

public class MapRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://www.squadlink.co.uk/MapRequest.php/";
    private Map<String, String> params;

    public MapRequest( String teamname, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("teamname", teamname);
        Log.i("Put Params","Inserted all");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
