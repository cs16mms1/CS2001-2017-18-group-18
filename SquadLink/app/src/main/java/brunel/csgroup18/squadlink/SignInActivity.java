package brunel.csgroup18.squadlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignInActivity extends AppCompatActivity {

    private Spinner sUserType;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        sUserType = (Spinner) findViewById(R.id.spinner_usertype);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        bLogin = (Button) findViewById(R.id.button_login);
        bRegister = (Button) findViewById(R.id.button_register);

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>
                (SignInActivity.this, android.R.layout.simple_selectable_list_item,getResources().getStringArray(R.array.userTypes));

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sUserType.setAdapter(userTypeAdapter);

    }
}
