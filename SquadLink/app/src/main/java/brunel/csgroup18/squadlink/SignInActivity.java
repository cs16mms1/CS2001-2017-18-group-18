package brunel.csgroup18.squadlink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {
    private Button nAthlete,nTrainer; //calling the button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        nTrainer = (Button)findViewById(R.id.trainer);
        nAthlete = (Button)findViewById(R.id.athlete);

        nTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this,TrainerLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        nAthlete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this,AthleteLoginActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        });


    }
}
