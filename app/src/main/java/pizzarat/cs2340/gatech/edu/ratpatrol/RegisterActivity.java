package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteBroker;

/**
 * This class represents the Registration Screen for the Rat Patrol app.
 */
public class RegisterActivity extends AppCompatActivity {


    private EditText userName;
    private EditText password;
    private CheckBox admin;
    private SQLiteBroker broker;

    /**
     * Creates the RegistrationActivity on startup.
     * @param savedInstanceState the information from the phone.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText userName = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);
        CheckBox admin = (CheckBox) findViewById(R.id.admin);
        SQLiteBroker broker = new SQLiteBroker();
    }

    public void register(View v){

        String user = userName.getText().toString();
        String pass = password.getText().toString();
        boolean adm = admin.isChecked();
        if (true//isValid(user,pass)
        ){
            //If so, fill into the database, and go to Login
            try {
                // Not much room to crash right now, but I don't really know how
                // we would handle one anyway.
                broker.writeToDb(user, pass, adm);
            } catch (Exception e){
                /**
                 *  Error Handling. If isValid does not catch the exception,
                 *  the program will bail to the welcome screen.
                 */
                Intent startNewActivity = new Intent(this, Welcome.class);
                startActivity(startNewActivity);
            }
            Intent startNewActivity = new Intent(this, LoginActivity.class);
            startActivity(startNewActivity);
        } else {
            //If not, clear fields
            userName.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }
    }

    /**
     *
     * @param userName: the userName input in the textField.
     * @param password: the password input in the textField.
     * @return returns whether or not the combination is valid and available.
     */
    private boolean isValid(String userName, String password){
        //make sure SQL does not have the String,
        //make sure all characters are legal!
        boolean validChars;
        if(userName.contains(":") || userName.contains("//") || userName.equals(null)){
            validChars = false;
        } else {
            validChars = true;
        }
        return (validChars && broker.credMatch(userName,password));
    }
}