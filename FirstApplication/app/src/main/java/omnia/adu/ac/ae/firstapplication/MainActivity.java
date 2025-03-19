package omnia.adu.ac.ae.firstapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText userFn, userLn;
    TextView result;
    Button applyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userFn = findViewById(R.id.UserFn);
        userLn = findViewById(R.id.UserLn);
        result = findViewById(R.id.Result);
        applyBtn = findViewById(R.id.ApplyButton);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = userFn.getText().toString();
                String ln = userLn.getText().toString();

                result.setText("Welcome, " + fn + " " + ln + "!");
            }
        });

    }
}