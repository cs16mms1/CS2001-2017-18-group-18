package brunel.csgroup18.squadlink;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverviewFragment extends Fragment {

    static String value="",value1="",id="";//Variable Description
    static int indexx=0;//Variable Description
    static  String item="";//Variable Description
    static String startdate="", enddate="", name="";//Variable Description
    private ProgressDialog progress;//ProgressDialog for Async Task (Background Network related Processes)
    static ArrayList<String> arrayList;
    static ArrayAdapter<String> adapter;
    static ListView lv;
    TextView nav;
    static String[] sessions;
    static  String result="";

    public OverviewFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {

            lv.findViewById(R.id.listview);
            lv.setAdapter(SignInActivity.adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    item = ((TextView) view).getText().toString();
                    String[] result = item.split("\n", 3); // Delimit the string upto 2nd line
                    result[0]=result[0].substring(result[0].indexOf(":")+1, result[0].length());//get first line
                    result[0]=result[0].trim();//trim the first line

                    result[1]=result[1].substring(result[1].indexOf(":")+1, result[1].length());//get first line
                    result[1]=result[1].trim();//trim the first line

                    result[2]=result[2].substring(result[2].indexOf(":")+1, result[2].length());//get first line
                    result[2]=result[2].trim();//trim the first line

                    name=result[0]+"";//store it in name
                    startdate=result[1]+"";
                    enddate=result[2]+"";
                    indexx = position;//store the position in indexx


                    try {
                        FirebaseDatabase database_b1 = FirebaseDatabase.getInstance();
                        String a=SignInActivity.UserEmail.getText()+"";
                        String usernamee=SignInActivity.getUserName(a+"");
                        final DatabaseReference myRefb1 = database_b1.getReference("Milestone").child(usernamee+"").child(result[0]+"");

                        myRefb1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    value="";
                                    value = dataSnapshot.getValue().toString();
                                    System.out.println("************************************/////***"+value+"");
                                } catch (Exception dfd) {
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                    } catch (Exception df) {}



                }
            });
        }catch (Exception ds){}
        return inflater.inflate(R.layout.fragment_overview, container, false);



    }

}
