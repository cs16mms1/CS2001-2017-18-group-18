package brunel.csgroup18.squadlink;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan Hoy on 06/02/2018.
 */

public class JoinTeamRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://www.squadlink.co.uk/JoinTeam.php/";
    private Map<String, String> params;

    public JoinTeamRequest( String user_id,String team_id, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("team_id", team_id);
        params.put("user_id", user_id);
        Log.i("Put Params","Inserted all");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}



