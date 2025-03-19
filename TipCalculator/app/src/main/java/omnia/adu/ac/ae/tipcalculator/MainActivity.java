package omnia.adu.ac.ae.tipcalculator;

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

public class MainActivity extends AppCompatActivity {

    private EditText userBill, userTip;
    TextView tipTotal, billTotal;
    Button calculateBtn;
    TipCalculator tipCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userBill = findViewById(R.id.user_bill);
        userTip = findViewById(R.id.user_tip);

        tipTotal = findViewById(R.id.tip_display);
        billTotal = findViewById(R.id.total_display);

        calculateBtn = findViewById(R.id.calculateBtn);

        TextChangeHandler changeHandler = new TextChangeHandler();

        userTip.addTextChangedListener(changeHandler);
    }
    public void calculate()
    {
        float bill = Float.parseFloat(userBill.getText().toString());
        float tip = Float.parseFloat(userTip.getText().toString());

        tipCalculator = new TipCalculator(tip, bill);

        float calculatedTip = tipCalculator.calculateTip();
        float calculatedBill =tipCalculator.calculateBill();

        displayBillAndTip(calculatedTip, calculatedBill);
    }

    private void displayBillAndTip(float tip, float bill)
    {
        tipTotal.setText(tip + " ");
        billTotal.setText(bill + " ");
    }

    class TextChangeHandler implements TextWatcher
    {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            calculate();
        }
    }
}

