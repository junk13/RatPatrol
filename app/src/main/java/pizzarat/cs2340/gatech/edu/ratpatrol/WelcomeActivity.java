package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * A class representing the WelcomeActivity screen in the Rat Patrol app.
 */
public class WelcomeActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    /**
     * Creates the welcome activity on startup
     * @param savedInstanceState the information from the phone
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcomeSplashActivity = new Intent(WelcomeActivity.this,
                        WelcomeSplashActivity.class);
                startActivity(welcomeSplashActivity);
            }

        }, SPLASH_TIME_OUT);
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
