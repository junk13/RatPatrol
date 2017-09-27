package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This is the selection screen after logging in which allow the
 * user to switch to different screens with different functions
 * using the displayed buttons.
 *
 * @author Harrison Banh
 */
public class SelectionScreenActivity extends AppCompatActivity {
    private View logoutButton;

    /**
     * Creates the SelectionScreenActivity
     * @param savedInstanceState the bundle from the last activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        // Logout Function
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });
    }

    /**
     * Switches to the LoginActivity from the SelectionScreenActivity
     * @param view the widget that triggers the switch
     */
    public void switchBackToLoginActivity(View view) {
        Intent startSelectionScreenActivity = new Intent(SelectionScreenActivity.this, LoginActivity.class);
        SelectionScreenActivity.this.startActivity(startSelectionScreenActivity);
    }

    /**
     * Switches to the WelcomeActivity from the SelectionScreenActivity
     * @param view the widget that triggers the switch
     */
    public void switchBackToWelcomeActivity(View view) {
        Intent switchToWelcomeActivity = new Intent(SelectionScreenActivity.this, Welcome.class);
        SelectionScreenActivity.this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Closes the SelectionScreenActivity thus "logging out" the user
     */
    // Make change later
    public void logout() {
       finish();
    }
}
