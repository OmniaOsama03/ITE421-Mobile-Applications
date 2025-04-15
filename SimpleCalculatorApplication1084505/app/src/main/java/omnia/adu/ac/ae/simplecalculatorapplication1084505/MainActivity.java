package omnia.adu.ac.ae.simplecalculatorapplication1084505;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static float num1 = 0;
    public static float num2 = 0;
    public static char operation = '+';

    private TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = findViewById(R.id.resultTextView);
    }

    public void openInputActivity(View view)
    {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        float result = 0;
        boolean valid = true;

        switch (operation)
        {
            case '+':
                result = num1 + num2;
                break;

            case '-':
                result = num1 - num2;
                if (result < 0) valid = false;
                break;

            case '*':
                result = num1 * num2;
                break;

            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    valid = false;
                }
                break;
        }

        if (valid) {
            if (operation == '/') {
                resultTV.setText(num1 + " " + operation + " " + num2 + " = " + String.format("%.2f", result));
            } else {
                resultTV.setText(num1 + " " + operation + " " + num2 + " = " + result);
            }
        } else {
            resultTV.setText("Invalid! (Negative or Division by Zero)");
        }
    }
}