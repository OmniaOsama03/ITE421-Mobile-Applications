package omnia.adu.ac.ae.candystore;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {


    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    // Build a View dynamically with all the candies
    public void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();
        if (candies.size() > 0) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            //create GridLayout view
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(candies.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView[] ids = new TextView[candies.size()]; //Notice how size is fixed
            EditText[][] namesAndPrices = new EditText[candies.size()][2];
            Button[] buttons = new Button[candies.size()];
            //Create inner class for ActionListener
            ButtonHandler bh = new ButtonHandler();

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;
            // for ( int i = 0 ; i <candy.size(); i++)
            for (Candy candy : candies) {
                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + candy.getId());

                // create the two EditTexts for the candy's name and price
                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][0].setText(candy.getName());
                namesAndPrices[i][1].setText("" + candy.getPrice());
                namesAndPrices[i][1]
                        .setInputType(InputType.TYPE_CLASS_NUMBER);
                namesAndPrices[i][0].setId(10 * candy.getId());
                namesAndPrices[i][1].setId(10 * candy.getId() + 1);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(candy.getId());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the elements to grid
                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            // retrieve name and price of the candy
            int candyId = v.getId();
            EditText nameET = findViewById(10 * candyId);
            EditText priceET = findViewById(10 * candyId + 1);
            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            // update candy in database
            try {
                double price = Double.parseDouble(priceString);
                dbManager.updateById(candyId, name, price);
                Toast.makeText(UpdateActivity.this, "Candy updated",
                        Toast.LENGTH_SHORT).show();

                // update screen
                updateView();
            } catch (NumberFormatException nfe) {
                Toast.makeText(UpdateActivity.this,
                        "Price error", Toast.LENGTH_LONG).show();
            }
        }
    }
}