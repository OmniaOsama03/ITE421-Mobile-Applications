package omnia.adu.ac.ae.midtermpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MortgageInfo extends AppCompatActivity {
    private TextView row0Column0, row0Column1, row0Column2;
    private Button changeTextButton;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_info);

        row0Column0 = findViewById(R.id.row0Column0);
        row0Column1 = findViewById(R.id.row0Column1);
        row0Column2 = findViewById(R.id.row0Column2);

        changeTextButton = findViewById(R.id.changeTextButton);

        user = new User();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        updateLayout();
    }
    public void changeText(View v)
    {
        Intent intent = new Intent(this,MortgageEdit.class);
        this.startActivity(intent);
    }

    private void updateLayout()
    {
        String name = user.getName();
        String favoriteColor = user.getFavoriteColor();
        int age = user.getAge();

        row0Column0.setText("Name: " + name);
        row0Column1.setText("Age: " + age);
        row0Column2.setText("Fave Color: " + favoriteColor);
    }


}