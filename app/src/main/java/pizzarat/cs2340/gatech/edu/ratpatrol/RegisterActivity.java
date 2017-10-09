package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pizzarat.cs2340.gatech.edu.sqlite.SQLiteCredBroker;

/**
 * This class represents the Registration Screen for the Rat Patrol app.
 */
public class RegisterActivity extends AppCompatActivity {


    private View userName;
    private View password;
    private View admin;
    private SQLiteCredBroker broker;

    /**
     * Creates the RegistrationActivity on startup.
     * @param savedInstanceState the information from the phone.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName =  findViewById(R.id.userName);
        password =  findViewById(R.id.password);
        admin = findViewById(R.id.admin);
        broker = new SQLiteCredBroker();
    }

    /**
     * Attempts to register new user, if successful, add to the SQL database.
     * If unsuccessful, clear fields and return some error to appear in a widget.
     * @param v view object
     */
    public void register(View v){

        String user = ((EditText)userName).getText().toString();
        String pass = ((EditText)password).getText().toString();
        boolean adm = ((CheckBox)admin).isChecked();
        System.out.println();
        ((EditText) userName).setError(null);
        if (isValid(user,pass)){
            try {
                broker.writeToCredDb(user, pass, adm, getApplicationContext());

                Intent startNewActivity = new Intent(this, LoginActivity.class);
                startActivity(startNewActivity);
            } catch (Exception e){
                Toast.makeText(getBaseContext(), "Email is already taken", Toast.LENGTH_SHORT).show();
            }

        } else {
            ((EditText)userName).setText("", TextView.BufferType.EDITABLE);
            ((EditText)password).setText("", TextView.BufferType.EDITABLE);
            Toast.makeText(getBaseContext(), "Invalid email and/or invalid password",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *      This method will check for invalid strings to make sure no
     *      funny buisness is going down.
     *
     * @param userName: the userName input in the textField.
     * @param password: the password input in the textField.
     * @return returns whether or not the combination is valid and available.
     */
    private boolean isValid(String userName, String password){
        //Make sure the String does not contain : or /, and that it isn't null.
        return !(userName.contains(":") || userName.contains("/") || userName.length() == 0)
                && userName.contains("@")
                && !password.isEmpty();
    }
}