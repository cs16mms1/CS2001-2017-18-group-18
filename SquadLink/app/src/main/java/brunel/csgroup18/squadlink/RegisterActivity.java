package brunel.csgroup18.squadlink;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private Spinner sUserType;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordRetype;
    private EditText etPhone;
    private EditText etFirstName;
    private EditText etLastName;
    private Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_provisional);

        sUserType = (Spinner) findViewById(R.id.spinner_usertype);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_new_password);
        etPasswordRetype = (EditText) findViewById(R.id.et_retype_password);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etFirstName = (EditText) findViewById(R.id.et_first_name);
        etLastName = (EditText) findViewById(R.id.et_last_name);
        bRegister = (Button) findViewById(R.id.button_registration);

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>
                (RegisterActivity.this, android.R.layout.simple_selectable_list_item,getResources().getStringArray(R.array.userTypes));

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sUserType.setAdapter(userTypeAdapter);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etUsername.getText().toString();
                String newPassword = etPassword.getText().toString();
                String passwordRetype = etPasswordRetype.getText().toString();
                String email = etEmail.getText().toString();
                String userType = sUserType.getSelectedItem().toString();
                String phone = etPhone.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                Log.i("Strings Parsed",username+newPassword+passwordRetype+email+userType);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("Response","Received response");
                        try {

                            Log.i("JSON",response);
                            JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            boolean success = jsonResponse.getBoolean("success");
                            Log.i("Success",jsonResponse.toString());

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                Log.i("Registration","Success");
                            }
                            else{
                                Log.i("Registration","Fail");
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                if(newPassword.equals(passwordRetype)){
                    RegisterRequest registerRequest = new RegisterRequest(username, newPassword, email,phone,userType, firstName, lastName, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }

                else{
                    Toast.makeText(RegisterActivity.this,"Passwords don't match",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
