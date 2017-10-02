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
    private View ratArchiveButton;
    private View userReportsButton;
    private View ratMapButton;

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

        // New York Rat Archive
        ratArchiveButton = findViewById(R.id.ratArchiveButton);
        ratArchiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToNYRatArchiveActivity();
            }
        });

        // User Reports Activity
        userReportsButton = findViewById(R.id.userReportsButton);
        userReportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToUserRatReportsActivity();
            }
        });

        // Rat Map Activity
        ratMapButton = findViewById(R.id.ratMapButton);
        ratMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRatMapActivity();
            }
        });
    }

    /**
     * Switches to the LoginActivity from the SelectionScreenActivity.
     */
    public void switchBackToLoginActivity() {
        Intent startSelectionScreenActivity = new Intent(SelectionScreenActivity.this, LoginActivity.class);
        SelectionScreenActivity.this.startActivity(startSelectionScreenActivity);
    }

    /**
     * Switches to the WelcomeActivity from the SelectionScreenActivity.
     */
    public void switchBackToWelcomeActivity() {
        Intent switchToWelcomeActivity = new Intent(SelectionScreenActivity.this, Welcome.class);
        SelectionScreenActivity.this.startActivity(switchToWelcomeActivity);
    }

    /**
     * Switches to the NewYorkRatArchiveActivity from the SelectionScreenActivity.
     */
    public void switchToNYRatArchiveActivity() {
        Intent switchToNYRatArchiveActivity = new Intent(this, NewYorkRatArchiveActivity.class);
        this.startActivity(switchToNYRatArchiveActivity);
    }

    /**
     * Switches to the UserRatReportsActivity.
     */
    public void switchToUserRatReportsActivity() {
        Intent switchToUserRatReportsActivity = new Intent(this, UserRatReportsActivity.class);
        this.startActivity(switchToUserRatReportsActivity);
    }

    /**
     * Switches to the RatMapActivity.
     */
    public void switchToRatMapActivity() {
        Intent switchToRatMapActivity = new Intent(this, RatMapActivity.class);
        this.startActivity(switchToRatMapActivity);
    }

    /**
     * Closes the SelectionScreenActivity thus "logging out" the user
     */
    // Make change later
    public void logout() {
       finish();
    }
}
