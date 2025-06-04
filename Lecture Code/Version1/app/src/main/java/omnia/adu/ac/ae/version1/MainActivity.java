package omnia.adu.ac.ae.version1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Hangman game;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        status = findViewById(R.id.status);

        if ( game == null )
            game = new Hangman( Hangman.DEFAULT_GUESSES );

        status.setText(""+game.getGuessesLeft());

    }
    public void startGame( View view){

    }
}