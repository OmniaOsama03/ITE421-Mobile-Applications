package omnia.adu.ac.ae.candystore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

    }

    public void insert(View v)
    {
        //Find the values
        EditText candyName = findViewById(R.id.nameTxt);
        EditText candyPrice = findViewById(R.id.priceTxt);

        //Extract the values
        String name = candyName.getText().toString();
        Float price = Float.parseFloat(candyPrice.getText().toString());

        //Clear the text
        candyName.setText("");
        candyPrice.setText("");

        Toast.makeText(this, name + " with price"  + price + " has been added!", Toast.LENGTH_LONG).show();
    }

    public void backToMain(View v)
    {
        finish(); //Will just take you back
    }
}