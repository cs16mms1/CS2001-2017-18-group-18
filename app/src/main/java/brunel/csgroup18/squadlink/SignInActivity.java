package brunel.csgroup18.squadlink;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    public static String PACKAGE_NAME;
    static String value="",value1="",id="";//Variable Description
    static String genius[],uid[],ulid;
    static int datab=0;
    static EditText UserEmail, UserPassword;
    static  String item="",ufname="",ulname="";
    static TextView txtView;
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    static ListView lv;
    static String[] sessions;
    static  String result="";
    static int num,lengthh;
    static int indexx=0;
    private ProgressDialog progress;//ProgressDialog for Async Task (Background Network related Processes)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        System.out.println("*************************"+PACKAGE_NAME);

        UserEmail = (EditText) findViewById(R.id.editText);//Initialise UserEmail
        UserEmail.setText("tanvir_ali@hotmail.co.uk");
        UserPassword = (EditText) findViewById(R.id.editText2);//Initialise Userpassword
        UserPassword.setText("tanviralix");
        FirebaseDatabase database_5 = FirebaseDatabase.getInstance();
        DatabaseReference myRef5 = database_5.getReference("Login");
        myRef5.child("I"+("1")).setValue("parth.prs.shah@gmail.com§parth1234§Parth§Shah§07858773873§");
        myRef5.child("I"+("2")).setValue("tanvir_ali@hotmail.co.uk§tanvirali§Tanvir§Ali§00000777777§");

        FirebaseDatabase database_b = FirebaseDatabase.getInstance();//Firebase Object
        final DatabaseReference myRefb = database_b.getReference("Login");//Child specific object
        myRefb.addListenerForSingleValueEvent(new ValueEventListener() { // value listener to get all data once in a go
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) // Through dataSnapShot
            {
                value1 = dataSnapshot.getValue().toString();//store all data in value1
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getUserName(String IDD)
    {
        String neww="";
        for(int i=0;i<IDD.length();i++)
        {
            char c=IDD.charAt(i);
            if(c=='@')
            {
                break;
            }
            else if(Character.isDigit(c)==true || Character.isAlphabetic(c)==true)
            {

                neww+=c+"";
            }
        }
        return neww;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        value1 = value1.substring(1, value1.length() - 1); // remove first and last character
        Scanner in = new Scanner(value1).useDelimiter("\\,"); //object "in" for scanner class created
        int count = 0;//initialise count to 0
        while (in.hasNext() == true) {
            datab++;
            String data = in.next();
            String ID = data.substring(data.indexOf("=") + 1, data.indexOf("§"));//Delimit user IDs
            System.out.println("***********************" + ID);//Display on console
            if (ID.equalsIgnoreCase(UserEmail.getText().toString()) == true) {//Check if user ID entered is correct
                ulid=ID+"";
                count++;//If correct, count +1
                Scanner inn = new Scanner(data).useDelimiter("\\§");// Scanner "inn"
                int cc = 0;
                while (inn.hasNext() == true) {//Loop to check password
                    String details = inn.next();
                    if (cc == 1) {
                        if (details.equals(UserPassword.getText().toString()) == true) {
                            ufname = inn.next();
                            ulname = inn.next();
                            new SignInActivity.PostClass(this).execute();
                        } else {
                            //txtView.setVisibility(View.VISIBLE);
                            break;
                        }
                    }
                    cc++;
                }
                break;
            }
        }
        if (count == 0) {
            // txtView.setText("Wrong Email ID");
            // txtView.setVisibility(View.VISIBLE);
        }

    }



}
