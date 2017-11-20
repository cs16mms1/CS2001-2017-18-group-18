package brunel.csgroup18.squadlink;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AthleteLoginActivity extends AppCompatActivity {

    private EditText nUsername, nPassword;
    private Button nLogin, nRegister;

    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_login);

        nAuth = FirebaseAuth.getInstance(); //gets current status of login
        firebaseAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){

                    Intent intent = new Intent(AthleteLoginActivity.this,MainActivity.class); //Takes to main activity
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        nUsername = (EditText) findViewById(R.id.username);
        nPassword = (EditText) findViewById(R.id.password);

        nLogin =    (Button)findViewById(R.id.login);
        nRegister = (Button)findViewById(R.id.register);

        nRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = nUsername.getText().toString();
                final String password = nPassword.getText().toString();
                nAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(AthleteLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(AthleteLoginActivity.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                        }else{
                            String user_id = nAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Athletes").child(user_id);
                            current_user_db.setValue(true);
                        }

                    }
                });
            }
        });

        nLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = nUsername.getText().toString();
                final String password = nPassword.getText().toString();
                nAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(AthleteLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(AthleteLoginActivity.this, "Sign In Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        nAuth.addAuthStateListener(firebaseAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        nAuth.removeAuthStateListener(firebaseAuthListner);
    }
}
