package omnia.adu.ac.ae.candystore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertActivity extends AppCompatActivity {

    Candy candy;
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        db = new DatabaseManager(this);

    }

    public void insert(View v)
    {
        //Find the values
        EditText candyName = findViewById(R.id.nameTxt);
        EditText candyPrice = findViewById(R.id.priceTxt);

        //Extract the values
        String name = candyName.getText().toString();

        String priceValue = candyPrice.getText().toString();

        //Use try in case we don't have access to the db or if the price has no proper value

        try {
            Float price = Float.parseFloat(priceValue);

            //Make the object
            Candy candy = new Candy(0, name, price);

            //Send the candy details to the db
            db.insertCandy(candy);

            //Toast.makeText(this, name + " with price"  + price + " has been added!", Toast.LENGTH_LONG).show();
            Toast.makeText(this, candy.toString(), Toast.LENGTH_LONG).show();

        }catch(NumberFormatException e)
        {
            // throw new RuntimeException();
            Log.w("CANDY INSERT", "In catch");
        }

         //Clear the text
         candyName.setText("");
         candyPrice.setText("");
    }

    public void backToMain(View v)
    {
        finish(); //Will just take you back
    }
}