package brunel.csgroup18.squadlink;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan Hoy on 25/11/2017.
 */

public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://www.squadlink.co.uk/LoginSecure.php/";
    private Map<String, String> params;

    public LoginRequest(String username, String password, String userType, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("type",userType);
        Log.i("Put Params","Inserted all");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}


