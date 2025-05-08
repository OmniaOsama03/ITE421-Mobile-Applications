package omnia.adu.ac.ae.ite421_assignment1_1084505;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

     //Omnia Osama Ahmed
    // 1084505
public class TaskOne extends AppCompatActivity {

    EditText passwordInput1, passwordInput2;
    TextView notificationText;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_one);

        passwordInput1 = findViewById(R.id.passwordInput1);
        passwordInput2 = findViewById(R.id.passwordInput2);
        notificationText = findViewById(R.id.notificationText);
        submitButton = findViewById(R.id.submitButton);

        PasswordWatcher pw = new PasswordWatcher();
        MatchWatcher mw = new MatchWatcher();

        passwordInput1.addTextChangedListener(pw);
        passwordInput2.addTextChangedListener(mw);

        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                notificationText.setText("Password IS ACCEPTED");
            }
        });


    }

    private class PasswordWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s)
        {
            String password1 = passwordInput1.getText().toString();

            if (isPasswordValid(password1))
            {
                passwordInput2.setEnabled(true);
                submitButton.setEnabled(false);
                notificationText.setText("");

            } else
            {
                passwordInput2.setEnabled(false);
                submitButton.setEnabled(false);
                notificationText.setText("WRONG PASSWORD");
            }
        }
    }

    private class MatchWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s)
        {
            String password1 = passwordInput1.getText().toString();
            String password2 = passwordInput2.getText().toString();

            if (password1.equals(password2))
            {
                submitButton.setEnabled(true);
                notificationText.setText("");
            } else
            {
                submitButton.setEnabled(false);
                notificationText.setText("PASSWORDS MUST MATCH");
            }
        }
    }

    private boolean isPasswordValid(String password)
    {
        //Rule 1: at least 8 characters
        if (password.length() < 8) return false;

        //Rule 2: Only valid characters
        if (!password.matches("[a-zA-Z0-9*%#&]+")) return false;

        //Rule 3: At least two digits
        int digitCount = 0;

        for (int character = 0; character < password.length(); character++)
        {
            char current = password.charAt(character);

            if (Character.isDigit(current))
                digitCount++;
        }

        if (digitCount < 2) return false;

        //Rule 4: Starts with uppercase or digit
        char firstChar = password.charAt(0);

        if (!(Character.isUpperCase(firstChar) || Character.isDigit(firstChar)))
            return false;

        //Rule 5: At least one uppercase
        if (!password.matches(".*[A-Z].*")) return false;

        // Rule 6: At least one lowercase
        if (!password.matches(".*[a-z].*")) return false;

        //Rule 7: Contains at least one of # or &
        if (!(password.contains("#") || password.contains("&")))
            return false;

        //Rule 8: No same letter in succession ( my implementation is case insensitive)
        for (int i = 1; i < password.length(); i++)
        {
            char prev = password.charAt(i - 1);
            char curr = password.charAt(i);

            if (Character.isLetter(prev) && Character.isLetter(curr) &&
                    Character.toLowerCase(prev) == Character.toLowerCase(curr)) {
                return false;
            }
        }

        return true;
    }

}