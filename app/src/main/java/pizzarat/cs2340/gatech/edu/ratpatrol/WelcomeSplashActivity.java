package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Funny intro popup before the Welcome Activity.
 *
 * @author Harrison Banh
 */
public class WelcomeSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_splash);

        // Start a thread to end the splash screen after a whilemn
        Thread welcomeStartUp = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(welcome);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        welcomeStartUp.start();
    }
}
