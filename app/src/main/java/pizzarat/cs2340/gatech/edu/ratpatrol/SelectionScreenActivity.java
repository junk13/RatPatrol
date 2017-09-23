package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * This is the selection screen after logging in which allow the
 * user to switch to different screens with different functions
 * using the displayed buttons.
 */
public class SelectionScreenActivity extends AppCompatActivity {
    private View logoutButton;

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

    public void switchBackToLoginActivity(View view) {
        Intent startSelectionScreenActivity = new Intent(SelectionScreenActivity.this, LoginActivity.class);
        SelectionScreenActivity.this.startActivity(startSelectionScreenActivity);
    }

    public void swithBackToWelcomeActivity(View view) {
        Intent switchToWelcomeActivity = new Intent(SelectionScreenActivity.this, Welcome.class);
        SelectionScreenActivity.this.startActivity(switchToWelcomeActivity);
    }

    // Make change later
    public void logout() {
       finish();
    }
}
