package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.app.LoaderManager;
import android.content.Intent; //added
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; //added

/**
 * A class representing the Welcome screen in the Rat Patrol app.
 */
public class Welcome extends AppCompatActivity{

    /**
     * Creates the welcome activity on startup
     * @param savedInstanceState the information from the phone
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * Switches to the LoginActivity.
     * @param v the widget that triggers the switch
     */
    public void logIn(View v){
        Intent startNewActivity = new Intent(this, LoginActivity.class);
        startActivity(startNewActivity);
    }

    /**
     * Switches to the RegisterActivty.
     * @param v the widget that triggers the switch.
     */
    public void register(View v){
        Intent startNewActivity = new Intent(this, RegisterActivity.class);
        startActivity(startNewActivity);
    }
}
