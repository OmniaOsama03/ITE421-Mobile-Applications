package omnia.adu.ac.ae.ite421_assignment1_1084505;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

    //Omnia Osama Ahmed
    // 1084505

public class TaskTwo extends AppCompatActivity {

    View colorBox;
    TextView colorName;
    Button changeColorButton;

    int[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    String[] colorLabels = {"RED", "GREEN", "BLUE"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_two);

        colorBox = findViewById(R.id.colorBox);
        colorName = findViewById(R.id.colorName);
        changeColorButton = findViewById(R.id.changeColorButton);

        changeColorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeColor();
            }
        });
    }

    private void changeColor()
    {
        Random random = new Random();
        int index = random.nextInt(colors.length);

        int selectedColor = colors[index];
        String selectedLabel = colorLabels[index];

        updateView(selectedColor, selectedLabel);
    }

    private void updateView(int selectedColor, String selectedLabel )
    {
        colorBox.setBackgroundColor(selectedColor);
        colorName.setText(selectedLabel);
    }
}