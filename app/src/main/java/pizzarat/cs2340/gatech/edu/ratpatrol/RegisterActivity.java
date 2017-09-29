package pizzarat.cs2340.gatech.edu.ratpatrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import pizzarat.cs2340.gatech.edu.sqlite.SQLiteBroker;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private SQLiteBroker broker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText userName = (EditText) findViewById(R.id.userName);
        EditText password = (EditText) findViewById(R.id.password);
        SQLiteBroker broker = new SQLiteBroker();
    }

//    public void register(View v){
//
//        String user = userName.getText().toString();
//        String pass = password.getText().toString();
//        if (isValid(user,pass)){
//            //If so, fill into the database, and go to Login
//            try {
//                broker.writeToCreDb(user, pass);
//            } catch (Exception DuplicateUserDbException){
//                /**
//                 *  Error Handling. If isValid does not catch the exception,
//                 *  the program will bail to the welcome screen.
//                 */
//                Intent startNewActivity = new Intent(this, Welcome.class);
//                startActivity(startNewActivity);
//            }
//            Intent startNewActivity = new Intent(this, LoginActivity.class);
//            startActivity(startNewActivity);
//        } else {
//            //If not, clear fields
//            userName.setText("", TextView.BufferType.EDITABLE);
//            password.setText("", TextView.BufferType.EDITABLE);
//        }
//    }
//
//    /**
//     *
//     * @param userName: the userName input in the textField.
//     * @param password: the password input in the textField.
//     * @return returns whether or not the combination is valid and available.
//     */
//    private boolean isValid(String userName, String password){
//        //make sure SQL does not have the String,
//        //make sure all characters are legal!
//        boolean validChars;
//        if(userName.contains(":") || userName.contains("//")){
//            validChars = false;
//        } else {
//            validChars = true;
//        }
//        return (validChars && broker.credMatch(userName,password));
//    }
}