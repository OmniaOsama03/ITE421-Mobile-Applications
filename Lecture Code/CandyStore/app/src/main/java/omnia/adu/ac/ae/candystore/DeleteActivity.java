package omnia.adu.ac.ae.candystore;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Not needed anymore: setContentView(R.layout.activity_delete);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView()
    {
        //Using the database manager to get the list of all candies.
        ArrayList<Candy> candies = dbManager.selectAll();

        //Creating the layout and scroll view
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        //Creating the RadioGroup element
        RadioGroup group = new RadioGroup(this);

        //Iterate over the list of candies and create the radio buttons with id, name, and price
        for(Candy candy: candies)
        {
            RadioButton rb = new RadioButton(this);

            rb.setId(candy.getId());
            rb.setText(candy.toString());

            //Add the radio button to the radio group
            group.addView(rb);
        }

        //Making a radio button dissapear when it get checked
        RadioButtonHander rbh = new RadioButtonHander();
        group.setOnCheckedChangeListener(rbh);

        //Create the back button
        Button back = new Button(this);
        back.setText("Back");
        back.setTextSize(20);

        //Add an onclick listener and call finish()
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //To make the view scrollable based on the radiogroup
        scrollView.addView(group);

        //Now, add the scrollview as part of the layout
        layout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 0);

        //Now, we can set the content view
        setContentView(layout);
    }

    class RadioButtonHander implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int id) {
            dbManager.deleteById(id);
            updateView();
        }
    }
}