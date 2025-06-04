package omnia.adu.ac.ae.lab2_1084505;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sentence extends AppCompatActivity {

    EditText sentenceInput;
    Button insertBtn, selectBtn, runBtn;
    TextView selectedSentenceTV;

    DatabaseManager dbManager;

    String selectedSentence = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        sentenceInput = findViewById(R.id.sentenceInput);
        insertBtn = findViewById(R.id.insertBtn);
        selectBtn = findViewById(R.id.selectBtn);
        runBtn = findViewById(R.id.runBtn);
        selectedSentenceTV = findViewById(R.id.selectedSentenceTV);


        dbManager = new DatabaseManager(this);

    }

    //Insert button
    public void insertSentence(View view)
    {
        String sentence = sentenceInput.getText().toString().trim();

        if (!sentence.isEmpty())
        {
            dbManager.insertSentence(sentence);

            Toast.makeText(this, "Your sentence was added", Toast.LENGTH_SHORT).show();

            Log.w("DB", "Sentence added: " + sentence);

        } else
        {

            Toast.makeText(this, "Enter a sentence.", Toast.LENGTH_SHORT).show();
        }
    }

    //Select button
    public void selectSentence(View view)
    {
        selectedSentence = dbManager.getRandomSentence();

        selectedSentenceTV.setText(selectedSentence);

        Log.w("DB", "Random sentence: " + selectedSentence);
    }

    //Run button
    public void runGame(View view)
    {

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("sentence", selectedSentence);
        startActivity(intent);

    }

}