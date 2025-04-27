package omnia.adu.ac.ae.midtermpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MortgageEdit extends AppCompatActivity {

    EditText nameED, ageED;
    RadioButton yellow, blue, green;
    Button changeTextButton;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_edit);

        nameED = findViewById(R.id.nameED);
        ageED = findViewById(R.id.ageED);

        yellow = findViewById(R.id.yellow);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);

        changeTextButton = findViewById(R.id.changeTextButton);

        user = MortgageInfo.user;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        updateLayout();
    }
    public void saveText(View v)
    {
        saveNewData();
        this.finish();
    }

    private void saveNewData()
    {
        String name = nameED.getText().toString();
        int age = Integer.parseInt(ageED.getText().toString());
        String favoriteColor;

        if(yellow.isChecked())
            favoriteColor = yellow.getText().toString();

        else if(blue.isChecked())
            favoriteColor = blue.getText().toString();

        else
            favoriteColor = green.getText().toString();

        user.setAge(age);
        user.setName(name);
        user.setFavoriteColor(favoriteColor);
    }

    private void updateLayout()
    {
        String name = user.getName();
        int age = user.getAge();
        String favoriteColor = user.getFavoriteColor();

        nameED.setText(name);
        ageED.setText(age + "");

        ArrayList<RadioButton> radioButtons = new ArrayList<>();

        radioButtons.add(yellow);
        radioButtons.add(blue);
        radioButtons.add(green);

        for(RadioButton rb: radioButtons)
        {
            if(rb.getText().toString().equalsIgnoreCase(favoriteColor))
                rb.setChecked(true);
        }
    }

}