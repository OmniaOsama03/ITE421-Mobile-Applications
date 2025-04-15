package omnia.adu.ac.ae.simplecalculatorapplication1084505;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {

    private EditText num1ET, num2ET;
    private RadioButton addRB, subRB, mulRB, divRB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        num1ET = findViewById(R.id.num1EditText);
        num2ET = findViewById(R.id.num2EditText);
        addRB = findViewById(R.id.addRB);
        subRB = findViewById(R.id.subRB);
        mulRB = findViewById(R.id.mulRB);
        divRB = findViewById(R.id.divRB);
    }

    public void submitData(View view)
    {
        String num1Str = num1ET.getText().toString();
        String num2Str = num2ET.getText().toString();

        if (!num1Str.equals("") && !num2Str.equals(""))
        {
            MainActivity.num1 = Float.parseFloat(num1Str);
            MainActivity.num2 = Float.parseFloat(num2Str);

            if (addRB.isChecked()) {
                MainActivity.operation = '+';
            } else if (subRB.isChecked()) {
                MainActivity.operation = '-';
            } else if (mulRB.isChecked()) {
                MainActivity.operation = '*';
            } else if (divRB.isChecked()) {
                MainActivity.operation = '/';
            }
        }

        finish();
    }
}