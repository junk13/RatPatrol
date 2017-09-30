package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteBroker;

/**
 * This class represents the Registration Screen for the Rat Patrol app.
 */
public class RegisterActivity extends AppCompatActivity {


    private View userName;
    private View password;
    private View admin;
    private SQLiteBroker broker;

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
        broker = new SQLiteBroker();
    }

    public void register(View v){

        String user = ((EditText)userName).getText().toString();
        String pass = ((EditText)password).getText().toString();
        boolean adm = ((CheckBox)admin).isChecked();
        System.out.println();
        ((EditText) userName).setError(null);
        if (isValid(user,pass)){
            //If so, fill into the database, and go to Login
            try {
                // Not much room to crash right now, but I don't really know how
                // we would handle one anyway.
                Log.d("hidden",""+user+" "+pass+" "+adm);
                broker.writeToDb(user, pass, adm, getApplicationContext());
                Log.d("hidden","1111");

                Intent startNewActivity = new Intent(this, LoginActivity.class);
                startActivity(startNewActivity);
            } catch (Exception e){
                Log.d("hidden",e.getLocalizedMessage());
                /**
                 *  Error Handling. If isValid does not catch the exception,
                 *  the program will bail to the welcome screen.
                 */

                ((EditText) userName).setError(getString(R.string.error_duplicate_user));
            }

        } else {
            //If not, clear fields
            ((EditText)userName).setText("", TextView.BufferType.EDITABLE);
            ((EditText)password).setText("", TextView.BufferType.EDITABLE);
            ((EditText) userName).setError(getString(R.string.error_invalid_username));
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
        //make sure SQL does not have the String,
        //make sure all characters are legal!
        boolean validChars;
        if(userName.contains(":") || userName.contains("/")  || userName.equals(null)){
            validChars = false;
        } else {
            validChars = true;
        }
        return (validChars);//&& broker.credMatch(userName,password));
    }
}