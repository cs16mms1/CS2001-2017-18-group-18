package brunel.csgroup18.squadlink;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan Hoy on 24/11/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://www.squadlink.co.uk/RegisterSecure.php/";
    private Map<String, String> params;

    public RegisterRequest(String username, String password, String email, String phone, String userType, String firstName, String lastName, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email",email);
        params.put("phone",phone);
        params.put("type",userType);
        params.put("first", firstName);
        params.put("last",lastName);
        Log.i("Put Params","Inserted all");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
