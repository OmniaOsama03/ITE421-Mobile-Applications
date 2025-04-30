package omnia.adu.ac.ae.programlayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout; //Do NOT use new here to avoid bugs
    private EditText fnTxt;
    private EditText snTxt;
    private TextView resultTV;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // 1. Create layout
        generateLayout();

        //2. Create the views you need and add the xml attributes
        generateFirstNumber();
        generateSecondNumber();
        generateResult();
        generateButton();

        // 3. Add Object layout (names can be more descriptive
        addObject1Layout();
        addObject2Layout();
        addObject3Layout();
        addObject4Layout();

        calculateButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                // Read the numbers and parse them into int
                int fn = Integer.parseInt(fnTxt.getText().toString());
                int sn = Integer.parseInt(snTxt.getText().toString());

                int res = fn + sn;

                //Write the result into the text view
                resultTV.setText("Result: " + res);
            }
        });
        // Add the objects to the view (You can apply Extract Method here to put all these in a method)
        relativeLayout.addView(fnTxt);
        relativeLayout.addView(snTxt);
        relativeLayout.addView(calculateButton);
        relativeLayout.addView(resultTV);

        // Set the view
        setContentView(relativeLayout);

    }

    private void generateLayout()
    {
        relativeLayout = new RelativeLayout(this);
        relativeLayout.setId(View.generateViewId());
    }

    private void generateFirstNumber()
    {
        fnTxt = new EditText(this);

        fnTxt.setId(View.generateViewId());
        fnTxt.setTextSize(25);
        fnTxt.setGravity(1);
        fnTxt.setHint("Enter First number: ");
    }

    private void generateButton()
    {
        calculateButton = new Button(this);

        calculateButton.setId(View.generateViewId());
        calculateButton.setTextSize(25);
        calculateButton.setGravity(1);
        calculateButton.setText("CALCULATE");
        //If you don't round corners or add bg color, it'll stay gray and a sharp rectangle

    }

    private void generateSecondNumber()
    {
        snTxt = new EditText(this);

        snTxt.setId(View.generateViewId());
        snTxt.setTextSize(25);
        snTxt.setGravity(1);
        snTxt.setHint("Enter Second number: ");
    }

    private void generateResult()
    {
        resultTV = new TextView(this);

        resultTV.setId(View.generateViewId());
        resultTV.setTextSize(25);
        resultTV.setGravity(1);
        resultTV.setHint("Results Appear Here!");
    }

    private void addObject1Layout()
    {
        //Set the height and width of the relative layout, respectively (in the params)
        RelativeLayout.LayoutParams firstNumber = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add rules
        firstNumber.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        firstNumber.setMargins(
                20, 50, 20, 0
        );

        // Add the rules to the object
        fnTxt.setLayoutParams(firstNumber);
    }

    private void addObject2Layout() {
        //Set the height and width of the relative layout, respectively (in the params)
        RelativeLayout.LayoutParams secondNumber = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add rules
        secondNumber.addRule(RelativeLayout.BELOW, fnTxt.getId()); // Below the first number
        secondNumber.setMargins(
                20, 50, 20, 0
        );

        // Add the rules to the object
        snTxt.setLayoutParams(secondNumber);
    }

    private void addObject3Layout()
    {
        //Set the height and width of the relative layout, respectively (in the params)
        RelativeLayout.LayoutParams calculateBtn = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add rules
        calculateBtn.addRule(RelativeLayout.BELOW, snTxt.getId());
        calculateBtn.setMargins(
                20, 50, 20, 0
        );

        // Add the rules to the object
        calculateButton.setLayoutParams(calculateBtn);
    }

    private void addObject4Layout()
    {
        //Set the height and width of the relative layout, respectively (in the params)
        RelativeLayout.LayoutParams resultsTextView = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        // Add rules
        resultsTextView.addRule(RelativeLayout.BELOW, calculateButton.getId());
        resultsTextView.setMargins(
                20, 50, 20, 0
        );

        // Add the rules to the object
        resultTV.setLayoutParams(resultsTextView);
    }
}