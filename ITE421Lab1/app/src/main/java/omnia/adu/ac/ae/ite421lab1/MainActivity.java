package omnia.adu.ac.ae.ite421lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText weightTxt, heightTxt;
    TextView resultTV;
    BMICalculator bmiCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightTxt = findViewById(R.id.weightTxt);
        heightTxt = findViewById(R.id.heightTxt);

        resultTV = findViewById(R.id.resultTV);

    }

    public void calculateBMI(View view)
    {
        double weight = Double.parseDouble(weightTxt.getText().toString());
        double height = Double.parseDouble(heightTxt.getText().toString());

        System.out.println("Weight: " + weight + " & height: " + height);
        bmiCalculator = new BMICalculator(weight, height);
        String fullText = bmiCalculator.getFullOutputText();

        resultTV.setText(fullText);
    }
}