package brunel.csgroup18.squadlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Scanner;

public class UserRegisterActivity extends AppCompatActivity {


    EditText UserFirstName, UserLastName, UserEmail, UserPassword, UserNumber;
    static String value=""; int c=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        FirebaseDatabase database_b = FirebaseDatabase.getInstance();
        final DatabaseReference myRefb = database_b.getReference("Login");
        myRefb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(c==1){
                    value = dataSnapshot.getValue().toString();c++;}
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        UserFirstName = (EditText) findViewById(R.id.First_Name_Reg);
        UserLastName = (EditText) findViewById(R.id.Last_Name_Reg);
        UserEmail = (EditText) findViewById(R.id.Email_Reg);
        UserPassword = (EditText) findViewById(R.id.Password_Reg);
        UserNumber = (EditText) findViewById(R.id.Number_Reg);
    }
    public void RegButton (View view)
    {
        String firstname = UserFirstName.getText().toString();
        String lastname = UserLastName.getText().toString();
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        String number = UserNumber.getText().toString();
        String value1=value.substring(1,value.length()-1);
        Scanner in=new Scanner(value1).useDelimiter("\\,");int count=0;
        while (in.hasNext()==true){
            in.next();
            count++;
        }
        FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
        DatabaseReference myRef5 = database_5.getReference("Login");
        myRef5.child("I"+(count)).setValue(email+"§"+password+"§"+firstname+"§"+lastname+"§"+number+"§");
        Toast.makeText(getBaseContext(),"Sucessfully Registered!",Toast.LENGTH_LONG).show();
        finish();
    }
}
