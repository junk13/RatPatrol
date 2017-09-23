package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.app.LoaderManager;
import android.content.Intent; //added
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //added

public class Welcome extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void logIn(View v){
        Intent startNewActivity = new Intent(this, LoginActivity.class);
        startActivity(startNewActivity);
    }

    public void register(View v){
        Intent startNewActivity = new Intent(this, RegisterActivity.class);
        startActivity(startNewActivity);
    }
}
