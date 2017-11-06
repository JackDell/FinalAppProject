package ca.uottawa.jackdell.choreapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method called when the 'Login' button from the MainActivity is clicked. The function gets the
     * inputted account email and username, and logs in the user.
     *
     * @param view
     */
    public void onBtnLoginClick(View view) {
        EditText emailInput = (EditText) findViewById(R.id.etEmailInput);
        EditText passInput = (EditText) findViewById(R.id.etPassInput);

        String email = emailInput.getText().toString();
        String password = passInput.getText().toString();

        DatabaseManager DM = new DatabaseManager(this.getBaseContext());
        Account account = DM.accountLogin(email);

        if(account == null) {
            // Let the user know that the account credentials are wrong, or the account doesn't exist
            return;
        }

        if(account.getPassword().equals(password)) {
            Session.setAccount(account);
            Intent intent = new Intent(this, ProfileLoginActivity.class);
            startActivity(intent);
        }
        else {
            // Incorrect password
        }
    }

    /**
     * Method called when the "New Account" button is clicked, it opens the CreateAccountActivity.
     *
     * @param view  the passed View object.
     */
    public void onBtnNewAccountClick(View view) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }
}
