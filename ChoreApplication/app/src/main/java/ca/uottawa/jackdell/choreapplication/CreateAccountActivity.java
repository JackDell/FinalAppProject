package ca.uottawa.jackdell.choreapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    /**
     * Method called when the 'Create Account' button from the CreateAccountActivity is clicked.
     * The function gets the inputted information from the name, email, and password field, and
     * creates a new account with it.
     * @param view  the passed View object.
     */
    public void onBtnCreateAccountClick(View view) {
        EditText nameInput = (EditText) findViewById(R.id.etAccName);
        EditText emailInput = (EditText) findViewById(R.id.etEmailInput);
        EditText passInput = (EditText) findViewById(R.id.etPassInput);

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        DatabaseManager DM = new DatabaseManager(this.getBaseContext());

        // Testing to see if an account has already been created using the inputted email
        if(DM.accountLogin(email) != null) {
            // If the email has already been used, do not allow the account to be created
            return;
        }

        Account account = new Account(name, email, password);
        DM.saveAccount(account);
    }
}
